import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/service/token.service';
import { VueloService } from 'src/app/service/vuelo.service';
import { VuelosComponent } from '../vuelos/vuelos.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLogged = false;

  constructor(private router:Router, private tokenService: TokenService,private vueloService: VueloService) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.isLogged=true;
    }else{
      this.isLogged=false;
    }
  }

  onLogOut():void{
    this.tokenService.logOut();
    window.location.reload();
  }

  login(){
    this.router.navigate(['/login'])
  }

  reservas(){
    this.router.navigate(['/reservas'])
  }

  /*
  cargarFiltrado(aeropuerto: string) : void{
    this.vueloService.getList().subscribe( resp=>{
      //console.log(resp[0].aeropuertoPartida.nombre == 'Ezeiza');
      VuelosComponent.  = resp.filter((elem)=> elem.aeropuertoLlegada.nombre == aeropuerto);
    })
  }*/

}
