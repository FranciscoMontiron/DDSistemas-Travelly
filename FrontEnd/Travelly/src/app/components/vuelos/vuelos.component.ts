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


  optionsOrigen: Aeropuerto[] = [];
  optionsDestino: Aeropuerto[] = [];
  filteredOptionsOrigen?: Observable<Aeropuerto[]>;
  filteredOptionsDestino?: Observable<Aeropuerto[]>;

  origen: any;
  destino: any;
  fecha: any; 

  fechaFormat: any;



  vuelos: Vuelo[] = [];

  vueloSeleccionado!: Vuelo;
  
  comprar = false;

  pasajeros : number = 1;

  imgAerolineas = {
    'Aerolineas Argentinas' : 'assets/Aeroarg.png'
  }
  

  constructor(
    private tokenService: TokenService,
    private vueloService: VueloService,
    private router: Router,
    private paisService: PaisService,
    private aeropuertoService: AeropuertoService
  ) {}

  isLogged = false;

  ngOnInit(): void {
    this.cargar();
    this.cargarPaises();
    this.comprar = false;
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }

    this.filteredOptionsOrigen = this.origenControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : value?.region;
        return name ? this._filterOrigen(name as string) : this.optionsOrigen.slice();
      }),
    );
    this.filteredOptionsDestino = this.origenControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : value?.region;
        return name ? this._filterDestino(name as string) : this.optionsDestino.slice();
      }),
    );
  }

  verFecha(): void {
    /*
    const anio = this.fecha.getFullYear();
    const mes = this.fecha.getMonth() + 1;
    const dia = this.fecha.getDate();
    const fechaformateada = `${anio}/${mes.toString().padStart(2, '0')}/${dia.toString().padStart(2, '0')}`;
    const objFecha = new Date(fechaformateada)*/

    console.log(this.fecha);
    console.log(this.origen)
    console.log(this.destino)
  
    
  }

  displayFnOrigen(aeropuerto: Aeropuerto): string {
    return aeropuerto && aeropuerto.region ? aeropuerto.region : '';
  }

  displayFnDestino(aeropuerto: Aeropuerto): string {
    return aeropuerto && aeropuerto.region ? aeropuerto.region : '';
  }

  private _filterOrigen(region: string): Aeropuerto[] {
    const filterValue = region.toLowerCase();

    return this.optionsOrigen.filter(option => option.region.toLowerCase().includes(filterValue));
  }

  private _filterDestino(region: string): Aeropuerto[] {
    const filterValue = region.toLowerCase();

    return this.optionsDestino.filter(option => option.region.toLowerCase().includes(filterValue));
  }

  async cargar(): Promise<any> {
    let vuelos = await this.vueloService.getList().toPromise();
    vuelos!.sort((x,y)=> x.precio - y.precio);
    let i = 0;
    for(let vuelo of vuelos!) {
      if(i < 30){
        this.vuelos.push(vuelo);
        i++;
      }else{break}
    }


    this.origen = '';
    this.destino = '';
    this.fecha = new Date('');
  }

  cargarPaises(): void {
    this.aeropuertoService.getList().subscribe((data) => {
      (this.optionsOrigen = data, this.optionsDestino = data);
    });
    console.log(this.optionsDestino, this.optionsOrigen);
  }

  recargar(): void {
    window.location.reload();
  }

  cargarFiltrado(): void {

    const anio = this.fecha.getFullYear();
    const mes = this.fecha.getMonth() + 1;
    const dia = this.fecha.getDate();
    const fechaformateada = `${anio}-${mes.toString().padStart(2, '0')}-${dia.toString().padStart(2, '0')} 00:00:00`;
    console.log(fechaformateada)

    //console.log(this.origen)
    //console.log(this.destino)

    this.vueloService.traerVueloFiltrados(fechaformateada,'Buenos Aires','Mendoza').subscribe((data) => {console.log(data),this.vuelos=data;}, err => console.log(err));
    /*this.vueloService.getList().subscribe((resp) => {
      this.vuelos = resp.filter(
        (elem) =>
          elem.aeropuertoLlegada.pais.nombre == this.destino &&
          elem.aeropuertoPartida.pais.nombre == this.origen
      );
    });*/
  }

  mensaje(): void {
    Swal.fire('Esta seccion va a estar disponible proximamente <3');
  }

    // material


  seleccionDeVuelo(vuelo: Vuelo): void {
    this.vueloSeleccionado=vuelo;
    this.comprar = !this.comprar
    //console.log(this.vueloSeleccionado);
  }

}
