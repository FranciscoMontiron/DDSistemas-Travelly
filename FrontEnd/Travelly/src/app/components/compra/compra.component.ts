import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Asiento } from 'src/app/model/asiento';
import { Pasajero } from 'src/app/model/pasajero';
import { Vuelo } from 'src/app/model/vuelo';
import { AsientosService } from 'src/app/service/asientos.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import { TokenService } from 'src/app/service/token.service';

import jwt_decode from 'jwt-decode';
import { Usuario } from 'src/app/model/usuario';
import { Reserva } from 'src/app/model/reserva';
import { Pago } from 'src/app/model/pago';
import { PagoService } from 'src/app/service/pago.service';

import * as moment from 'moment-timezone';
import { ReservasService } from 'src/app/service/reservas.service';
import { PasajeroService } from 'src/app/service/pasajero.service';
import Swal from 'sweetalert2';


  @Component({
    selector: 'app-compra',
    templateUrl: './compra.component.html',
    styleUrls: ['./compra.component.css']
  })
  export class CompraComponent implements OnInit {
  
    @Input() vuelo: any;
  /*
    firstFormGroup = this.fb.group({
      firstCtrl: ['', Validators.required],
    });*/
    
    user: any;
  
    metodoDePago: any;
  
    nombreUsuario: any;
    listaDeUsuarios! : Usuario[];
    usuario!: Usuario;
  
  
    //----- Pago ---------------------------
    
    options = [
      { value: 1 , label: 'Credito' },
      { value: 2, label: 'Debito' },
      { value: 3, label: 'MercadoPago' },
      { value: 4, label: 'Prisma'}
    ];
  
    numeroDeTarjeta: number = 0;
    fechaDeCaducidad: string = '12/12/2020';
    codigoDeSeguridad: number = 0;
    cuotas: number = 1;
  
    pago! : any;
  
    reservaAct!: any;
  
    
  //----------------------------------
  
    fb!: FormGroup;
    isLinear = true;
  
    pasajeros: Pasajero[] = [];
    pasajerosCreados: Pasajero[] = [];
    infoPasajeros: Pasajero[] = [];
    
    mostrar = false;
  
    precioDeAsientos: number = 0;
    precioIVA: number = 0;
    precioFinal: number = 0;
  
    // --------------seleccion de butacas------------------
    asientos: Asiento[] = [];
    asientosDisponibles: Asiento[] = [];
  
    // -------------- Datos de los pasajeros --------------
    asientosSeleccionados: Asiento[] = [];
      // --- FormArray --
        fg!: FormGroup
        dataSourcePacks!: MatTableDataSource<any>;
        displayedColumns = ["nombre", "apellido", "dni"]
      
        nombre = new FormControl('',Validators.required);
        apellido = new FormControl('',Validators.required);
        dni = new FormControl(0,Validators.required);
  
        
  
    constructor(private tokenService: TokenService, private usuarioService: UsuarioService ,private _fb: FormBuilder, private asientoService: AsientosService, private cd: ChangeDetectorRef, private pagoService: PagoService, private reservaService:ReservasService, private pasajeroService: PasajeroService){}
  
    isLogged = false;
    
    
    
    ngOnInit(): void {
  
      const moment = require('moment-timezone');
      moment.tz.setDefault('America/Argentina/Buenos_Aires');
  
      if (this.tokenService.getToken()) {
        this.isLogged = true;
        this.obtenerUsuario();
      } else {
        this.isLogged = false;
      }
      this.cargarAsientosDisponibles();
  
      this.fg = this._fb.group({
        cantDesde: this.nombre,
        precio: this.apellido,
        dni: this.dni,
        formsPasajeros: this._fb.array([])
      });
    }  
    
    async obtenerUsuario(): Promise<void>{
  
      const listaUsuarios  = await this.usuarioService.getList().toPromise();
      let usuarioEncotrado: Usuario;
  
      let token = this.tokenService.getToken()
      let decoded = jwt_decode(token);
      this.nombreUsuario = decoded;
      let nombreUsuario = this.nombreUsuario.sub; 
  
      listaUsuarios?.forEach(function(usuario: Usuario){
        if(usuario.nombreUsuario == nombreUsuario){
          usuarioEncotrado = usuario;
        }})
  
      this.usuario = usuarioEncotrado!;
  
      const usuarioObj = await this.usuarioService.getUsuario(this.usuario.id!).toPromise();
      this.usuario = usuarioObj!;
  
    }
  
      pagar():void   {
  
      if(this.numeroDeTarjeta != 0 && this.codigoDeSeguridad != 0){      
        let fecha: Date = moment().toDate();
        console.log(this.usuario)
  
        const pago = new Pago(fecha,this.precioFinal,this.reservaAct);
        this.pagoService.crearPago(pago).subscribe(data => {this.pago = data});
  
        let reservaModi = new Reserva('pago',fecha,this.precioFinal,this.usuario,this.vuelo);
        this.reservaService.update(this.reservaAct.id, reservaModi).subscribe(data => {});
  
        alert('Pago realizado con exito');
      }else{
        alert('No se puedo realizar el pago');
      }
    }
  
    reservar():void {
      let fecha: Date = moment().toDate();
  
      const reserva = new Reserva('pendiente',fecha,this.precioFinal,this.usuario,this.vuelo);
      this.reservaService.crearReserva(reserva).subscribe(data => {this.reservaAct = data,Swal.fire(
        'Se ha reservado con exito!!',
        'Recuerda que puede gestionar su reserva desde "Mis reservas" o continuar con el pago ahora mismo',
        'success'
      )});
      
    }
  
    async crearPasajeros(): Promise<any>{
      this.pasajeroService.getList().subscribe(data=>{});
  
      let datos = this.formsPasajeros.value;
      let i: number = 0;
      for(let dato of datos){
        
        let aSelect = this.asientosSeleccionados[i];
        const pasajeros = await this.pasajeroService.save(dato).toPromise();
        let asientoModi = new Asiento(false,aSelect.seleccionado,aSelect.numero,aSelect.clase,aSelect.asientoColumna,pasajeros,this.vuelo.avion);
        this.asientoService.update(aSelect.id!,asientoModi).subscribe(data => {});
        i++;
      }
  
    }
  
    optionSelected() {  
      console.log(`Option selected: ${this.metodoDePago}`);
    }
  
    get formsPasajeros() {
      return this.fg.controls["formsPasajeros"] as FormArray;
    };
  
    addPersona(): void {
      const personaForm = this._fb.group({
        nombre: [''],
        apellido: [''],
        dni: [0]
      });
  
      this.formsPasajeros.push(personaForm);
      this.dataSourcePacks = new MatTableDataSource(this.formsPasajeros.controls);
  
      this.cd.detectChanges();
    }
  
  
    datosPasajeros(): void {
       for (let i = 0; i < this.asientosSeleccionados.length; i++){
        this.pasajeros.push(new Pasajero("","",0));
        this.addPersona(); 
       }
    }
  
    cargarAsientosDisponibles(): void {
        this.asientosDisponibles = [];
        for (let asiento of this.vuelo.avion.asientos) {
          if (asiento.estado){
            this.asientosDisponibles.push(asiento);  
          }
        }
  
        this.asientosDisponibles.sort((x,y)=> x.numero - y.numero);
  
    }
  
    reserva(asientoSelecionado: Asiento): void {
  
      let indice = this.asientosDisponibles.findIndex(asiento => asiento.id === asientoSelecionado.id);
      var lista = this.asientosDisponibles
      lista[indice].seleccionado = !lista[indice].seleccionado;
      this.asientosDisponibles = lista;
      
      if(this.asientosSeleccionados.includes(asientoSelecionado)){
        let indiceSelect = this.asientosSeleccionados.findIndex(asiento => asiento.id === asientoSelecionado.id);
        this.asientosSeleccionados.splice(indiceSelect);
        
        if(asientoSelecionado.clase == 'Ejecutiva'){
          this.precioDeAsientos -= (this.vuelo.precio + this.vuelo.precio * 0.5);
        }else if(asientoSelecionado.clase == 'Premium'){
          this.precioDeAsientos -= (this.vuelo.precio + this.vuelo.precio * 0.25);
        }else {
          this.precioDeAsientos -= this.vuelo.precio;
        }
        
      }else{
        this.asientosSeleccionados.push(asientoSelecionado);
        if(asientoSelecionado.clase == 'Ejecutiva'){
          this.precioDeAsientos += (this.vuelo.precio + this.vuelo.precio * 0.5);
        }else if(asientoSelecionado.clase == 'Premium'){
          this.precioDeAsientos += (this.vuelo.precio + this.vuelo.precio * 0.25);
        }else {
          this.precioDeAsientos += this.vuelo.precio;
        }
      }
  
      console.log(this.asientosSeleccionados);
    }
    
    submitFormPasajeros(): void {
      console.log(this.formsPasajeros.value);
      this.infoPasajeros = this.formsPasajeros.value;
      this.precioIVA = this.precioDeAsientos * 0.21;
      this.precioFinal =this.precioDeAsientos + this.precioIVA;
  
    }
  
    async mostrarVuelo() {
  
      console.log(this.vuelo);  
      console.log(this.asientosSeleccionados);
    }
  
  
  }
  