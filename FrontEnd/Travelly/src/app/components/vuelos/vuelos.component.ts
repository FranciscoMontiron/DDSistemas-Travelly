import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { RouteConfigLoadEnd, Router } from '@angular/router';
import { map, Observable, startWith } from 'rxjs';
import { Pais } from 'src/app/model/pais';
import { Vuelo } from 'src/app/model/vuelo';
import { PaisService } from 'src/app/service/pais.service';
import { TokenService } from 'src/app/service/token.service';
import { VueloService } from 'src/app/service/vuelo.service';
import Swal from 'sweetalert2';
import { ErrorStateMatcher } from '@angular/material/core';
import { Aeropuerto } from 'src/app/model/aeropuerto';
import { AeropuertoService } from 'src/app/service/aeropuerto.service';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-vuelos',
  templateUrl: './vuelos.component.html',
  styleUrls: ['./vuelos.component.css'],
})
export class VuelosComponent implements OnInit {

  matcher = new MyErrorStateMatcher();

  origenControl = new FormControl<string | Aeropuerto>('', [Validators.required]);
  destinoControl = new FormControl<string | Aeropuerto>('', [Validators.required]);
  fechaControl = new FormControl(Date, [Validators.required]);

  camposCompletos: boolean = false;    

  options: Aeropuerto[] = [];

  filteredOrigenes?: Observable<any[]>;
  filteredDestinos?: Observable<any[]>;

  origen: any;
  destino: any;
  fecha: any; 
  fechaFormat: any;

  vuelos: Vuelo[] = [];
  vueloSeleccionado!: Vuelo;
  
  comprar = false;

  constructor(
    private tokenService: TokenService,
    private vueloService: VueloService,
    private aeropuertoService: AeropuertoService
  ) {}

  isLogged = false;

  ngOnInit(): void {
    this.cargar();
    this.cargarPaises();
    this.comprar = false;
      this.origenControl.valueChanges.subscribe(value => this.actualizarCamposCompletos());
      this.destinoControl.valueChanges.subscribe(value => this.actualizarCamposCompletos());
      this.fechaControl.valueChanges.subscribe(value => this.actualizarCamposCompletos());
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }

    this.filteredOrigenes = this.origenControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : value?.region;
        return name ? this._filter(name as string) : this.options.slice();
      }),
    );

    this.filteredDestinos = this.destinoControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : value?.region;
        return name ? this._filter(name as string) : this.options.slice();
      }),
    );


  }

  displayFn(aeropuerto: Aeropuerto): string {
    console.log('selected aeropuerto:', aeropuerto);
    return aeropuerto && aeropuerto.region ? aeropuerto.region : '';
  }

  private _filter(region: string): Aeropuerto[] {
    const filterValue = region.toLowerCase();
    return this.options.filter(option => option.region.toLowerCase().includes(filterValue));
  }

  actualizarCamposCompletos() {
    // Comprobar si los tres campos tienen valores no nulos y no vacíos
    this.camposCompletos = Boolean(this.origenControl.value && this.destinoControl.value && this.fechaControl.value);
  }
    


  async cargar(): Promise<any> {
    let vuelos = await this.vueloService.getList().toPromise();
    vuelos!.sort((x,y)=> x.precio - y.precio);
    let i = 0;
    for(let vuelo of vuelos!) {
      if(i < 5){
        this.vuelos.push(vuelo);
        i++;
      }else{break}
    }
    this.origen = undefined;
    this.destino = undefined;
    this.fecha = undefined; 
  }

  cargarPaises(): void {

    this.aeropuertoService.getList().subscribe((data) => {
      this.options = data.map((aeropuerto) => {
        return {
          codigo: aeropuerto.codigo,
          latitud: aeropuerto.latitud,
          longitud: aeropuerto.longitud,
          nombre: aeropuerto.nombre,
          region: aeropuerto.region,
          pais: aeropuerto.pais
        };
      });
    });
    console.log(this.options);
  }

  recargar(): void {
    window.location.reload();
  }

  cargarFiltrado(): void {
    if(this.camposCompletos){
      const anio = this.fecha.getFullYear();
      const mes = this.fecha.getMonth() + 1;
      const dia = this.fecha.getDate();
      const fechaformateada = `${anio}-${mes.toString().padStart(2, '0')}-${dia.toString().padStart(2, '0')} 00:00:00`;
      //console.log(fechaformateada)
  
      this.vueloService.traerVueloFiltrados(fechaformateada,this.origen.region,this.destino.region).subscribe((data) => {this.vuelos=data}, err => console.log(err));
    }else{
      Swal.fire({
        icon: 'warning',
        title: 'Oops...',
        text: 'Por favor complete origen, destino y fecha antes de buscar'
      })
    }
    

  }

  mensaje(): void {
    Swal.fire('Esta seccion va a estar disponible proximamente <3');
  }


  seleccionDeVuelo(vuelo: Vuelo): void {
    this.vueloSeleccionado=vuelo;
    this.comprar = !this.comprar
  }

}
