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

  myControl = new FormControl<string | Pais>('', [Validators.required]);
  options: Pais[] = [];
  filteredOptions?: Observable<Pais[]>;

  origen: string = '';
  destino: string = '';
  fecha: Date = new Date('');

  vuelos: Vuelo[] = [];

  vueloSeleccionado!: Vuelo;
  
  comprar = false;

  pasajeros : number = 1;
  

  constructor(
    private tokenService: TokenService,
    private vueloService: VueloService,
    private router: Router,
    private paisService: PaisService
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

    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : value?.nombre;
        return name ? this._filter(name as string) : this.options.slice();
      }),
    );
  }

  displayFn(pais: Pais): string {
    return pais && pais.nombre ? pais.nombre : '';
  }

  private _filter(pais: string): Pais[] {
    const filterValue = pais.toLowerCase();

    return this.options.filter(option => option.nombre.toLowerCase().includes(filterValue));
  }

  async cargar(): Promise<any> {
    let vuelos = await this.vueloService.getList().toPromise();
    vuelos!.sort((x,y)=> x.precio - y.precio);
    let i = 0;
    for(let vuelo of vuelos!) {
      if(i < 4){
        this.vuelos.push(vuelo);
        i++;
      }else{break}
    }


    this.origen = '';
    this.destino = '';
    this.fecha = new Date('');
  }

  cargarPaises(): void {
    this.paisService.getList().subscribe((data) => {
      (this.options = data), console.log(data);
    });
  }

  recargar(): void {
    window.location.reload();
  }

  cargarFiltrado(): void {
    this.vueloService.getList().subscribe((resp) => {
      this.vuelos = resp.filter(
        (elem) =>
          elem.aeropuertoLlegada.pais.nombre == this.destino &&
          elem.aeropuertoPartida.pais.nombre == this.origen
      );
    });
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
