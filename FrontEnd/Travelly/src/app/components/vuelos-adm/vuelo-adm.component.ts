import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map, Observable, startWith } from 'rxjs';
import { Aeropuerto } from 'src/app/model/aeropuerto';
import { Avion } from 'src/app/model/avion';
import { Vuelo } from 'src/app/model/vuelo';
import { AeropuertoService } from 'src/app/service/aeropuerto.service';
import { TokenService } from 'src/app/service/token.service';
import { VueloService } from 'src/app/service/vuelo.service';
import Swal from 'sweetalert2';
import { MyErrorStateMatcher } from '../vuelos/vuelos.component';
import { DialogADMComponent } from '../dialog-adm/dialog-adm.component'
import { AvionService } from 'src/app/service/avion.service';

@Component({
  selector: 'app-vuelo-adm',
  templateUrl: './vuelo-adm.component.html',
  styleUrls: ['./vuelo-adm.component.css']
})
export class VueloAdmComponent implements OnInit {

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

  aeropuertos: Aeropuerto[] = [];
  aviones: Avion[] = [];
  vuelos: Vuelo[] = [];

  vueloSeleccionado!: Vuelo;
  
  dialogResult: any;

  imgAerolineas = {
    'Aerolineas Argentinas' : 'assets/Aeroarg.png'
  }
  

  constructor(
    private tokenService: TokenService,
    private vueloService: VueloService,
    private aeropuertoService: AeropuertoService,
    private avionService: AvionService,
    public dialog: MatDialog
  ) {}

  isLogged = false;

  ngOnInit(): void {
    this.cargar();
    this.cargarPaises();
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

  abrirDialogo(operacion: string, vuelo: Vuelo) {
    const dialogRef = this.dialog.open(DialogADMComponent, {
      data: {
        aviones: this.aviones,
        aeropuertos: this.aeropuertos,
        operacion: operacion,
        vuelo: vuelo
      }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      this.dialogResult =  result;
      this.cargar();
    });
  }

  abrirDialogoCrear(operacion: string) {
    const dialogRef = this.dialog.open(DialogADMComponent, {
      data: {
        aviones: this.aviones,
        aeropuertos: this.aeropuertos,
        operacion: operacion,
      }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      this.dialogResult =  result;
      this.cargar();
    });
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
    this.vuelos = vuelos!.sort((x,y)=> x.precio - y.precio);
    

    let aviones= await this.avionService.getList().toPromise();
    this.aviones = aviones!;

    let aeropuertos = await this.aeropuertoService.getList().toPromise();
    this.aeropuertos = aeropuertos!;

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

    this.vueloService.traerVueloFiltrados(fechaformateada,this.origen.region,this.destino.region).subscribe((data) => {console.log(data),this.vuelos=data;}, err => console.log(err));
  }

  editarVuelo(): void {
    let fechaPartida = this.dialogResult.fechaHoraPartida;
    let fechaArribo = this.dialogResult.fechaHoraArribo;

    let vuelo = new Vuelo(fechaArribo,fechaPartida,this.dialogResult.precio,this.dialogResult.avion,this.dialogResult.aeropuertoPartida,this.dialogResult.aeropuertoLlegada);
  }

  borrar(vuelo: Vuelo): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success m-3 px-3',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: 'Estas Seguro?',
      text: "Este cambio sera permanente",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si  ',
      cancelButtonText: 'No',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.vueloService.delete(vuelo.id).subscribe(data => {console.log(data),this.cargar();})
        swalWithBootstrapButtons.fire(
          'Borrado!',
          'El vuelo se a eliminado correctamente',
          'success'
        )
      } else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cacelado',
          'El vuelo no sufrio ninguna cambio!',
          'error'
        )
      }
    })
  }

}

