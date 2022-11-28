import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Aeropuerto } from 'src/app/model/aeropuerto';
import { Asiento } from 'src/app/model/asiento';
import { Avion } from 'src/app/model/avion';
import { Pais } from 'src/app/model/pais';
import { Pasajero } from 'src/app/model/pasajero';
import { Vuelo } from 'src/app/model/vuelo';
import { TokenService } from 'src/app/service/token.service';
import { VueloService } from 'src/app/service/vuelo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-vuelo-adm',
  templateUrl: './vuelo-adm.component.html',
  styleUrls: ['./vuelo-adm.component.css']
})
export class VueloAdmComponent implements OnInit {

  origen: string = '';
  destino: string = '';
  fecha: Date = new Date("");

  fechaYHoraArribo = new Date("");
  fechaYHoraPartida = new Date("");
  precio: number = 0;
  avion: Avion = new Avion(0,"", "",[]);
  aeropuertoLlegada: Aeropuerto = new Aeropuerto("","","","","",new Pais(0,""))
  aeropuertoPartida : Aeropuerto = new Aeropuerto("","","","","",new Pais(0,""))

  closeResult: string = "";

  v: Vuelo = new Vuelo(new Date(""),new Date(""),0,new Avion(0,"", "",[]),new Aeropuerto("","","","","",new Pais(0,"")),new Aeropuerto("","","","","",new Pais(0,"")));

  vuelos: Vuelo[] = [];



  constructor(private tokenService: TokenService, private vueloService: VueloService,private router:Router, private modalService: NgbModal) {}

  isLogged = false;

  ngOnInit(): void {

    this.cargar();

    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

  cargar() : void{
    this.vueloService.getList().subscribe(data => {this.vuelos = data, console.log(data)});
    this.origen="";
    this.destino="";
    this.fecha= new Date("");

  }

  recargar() : void{
    window.location.reload();
  }



  cargarFiltrado() : void{
    this.vueloService.getList().subscribe( resp=>{
      this.vuelos = resp.filter((elem)=> elem.aeropuertoLlegada.pais.nombre == this.destino && elem.aeropuertoPartida.pais.nombre == this.origen);
    })
  }
  

  delete(id?: number){

    Swal.fire({
      title: '¿Estas seguro?',
      text: "Este cambio no se puede revertir",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si!',
      cancelButtonText: 'No!'
    }).then((result) => {
      if (result.isConfirmed) {
        if( id!= undefined){
          this.vueloService.delete(id).subscribe(data =>{
            this.cargar();
          }, err => {
            alert("No se puedo borrar el vuelo");
          })
        }
        Swal.fire(
          'Borrardo!',
          'Su vuelo se elimino con exito',
          'success'
        )
      }
    })
  }

  open(content: any) {
    this.fechaYHoraArribo = new Date("");
    this.fechaYHoraPartida = new Date("");
    this.precio= 0;
    this.avion = new Avion(0,"", "",[]);
    this.aeropuertoLlegada = new Aeropuerto("","","","","",new Pais(0,""))
    this.aeropuertoPartida = new Aeropuerto("","","","","",new Pais(0,""))

    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(async (result) => {
      this.closeResult = `Closed with: ${result}`;
      const e = new Vuelo(this.fechaYHoraArribo, this.fechaYHoraPartida,this.precio, this.avion, this.aeropuertoLlegada,this.aeropuertoPartida);
      this.vueloService.save(e).subscribe(
        data => {
          this.cargar();
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Vuelo añadido',
            showConfirmButton: false,
            timer: 1700

})
        }, err => {
          alert("Fallo");
        }
      )
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    },
    );
  }

  update(content: any, id:number) {
    this.vueloService.getVuelo(id).subscribe(
      data => {
        this.v=data;
        this.fechaYHoraArribo=data.fechaYHoraArribo;
        this.fechaYHoraPartida=data.fechaYHoraPartida;
        this.precio=data.precio;
        this.avion=data.avion;
        this.aeropuertoPartida=data.aeropuertoPartida;
        this.aeropuertoLlegada=data.aeropuertoLlegada;
      },err => { alert ("Fallo");}
    )
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(async (result) => {
      this.closeResult = `Closed with: ${result}`;
      const expe = new Vuelo(this.fechaYHoraArribo, this.fechaYHoraPartida,this.precio, this.avion, this.aeropuertoLlegada,this.aeropuertoPartida);
      this.vueloService.update(id,expe).subscribe(
        data => {
          this.cargar();
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Vuelo actualizado',
            showConfirmButton: false,
            timer: 1700

})
        }, err => {
          alert("Fallo");
        }
      )
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    },
    );
  }



  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }



}
