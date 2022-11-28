import { Component, OnInit } from '@angular/core';
import { RouteConfigLoadEnd, Router } from '@angular/router';
import { Vuelo } from 'src/app/model/vuelo';
import { TokenService } from 'src/app/service/token.service';
import { VueloService } from 'src/app/service/vuelo.service';

@Component({
  selector: 'app-vuelos',
  templateUrl: './vuelos.component.html',
  styleUrls: ['./vuelos.component.css'],
})
export class VuelosComponent implements OnInit {

  origen: string = '';
  destino: string = '';
  fecha: Date = new Date("");

  vuelos: Vuelo[] = [];



  constructor(private tokenService: TokenService, private vueloService: VueloService,private router:Router) {}

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
  



}
