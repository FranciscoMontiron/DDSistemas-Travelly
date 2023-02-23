import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reserva } from 'src/app/model/reserva';
import { Usuario } from 'src/app/model/usuario';
import { ReservasService } from 'src/app/service/reservas.service';
import { TokenService } from 'src/app/service/token.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import Swal from 'sweetalert2';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent implements OnInit {

  reservas!: any;
  usuario: any;
  nombreUsuario: any;


  constructor(private tokenService: TokenService, private reservaService: ReservasService,private router:Router, private usuarioService: UsuarioService) { }

  isLogged = false;

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.obtenerReservasDelUsuario();
    } else {
      this.isLogged = false;
    }
  }


  cancelar(reserva: any): void {
    
    let fechaActual = new Date();
    if(fechaActual >= reserva.fechaYHora){
      alert("El vuelo ya Expiro!");
        
    }else{
    
    Swal.fire({
      title: '¿Estas seguro?',
      text: "Este cambio no se puede revertir, las devoluciones tardan 30 dias habiles",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si!',
      cancelButtonText: 'No!'
    }).then((result) => {
      if (result.isConfirmed) {
        if( reserva.id!= undefined){
          let reservaModi = new Reserva('cancelada',fechaActual,this.usuario,reserva.vuelo);
          this.reservaService.update(reserva.id, reservaModi).subscribe(data => {
            this.obtenerReservasDelUsuario();
          }, err => {
            alert("No se puedo borrar la reserva");
          })
        }
        Swal.fire(
          'Borrardo!',
          'Su reserva a sido eliminada con exito, El pago se reembolsara en 30 dias habiles...',
          'success'
        )
      }
    })
  }
}

  async obtenerReservasDelUsuario(): Promise<void>{

    const listaUsuarios  = await this.usuarioService.getList().toPromise();
    let usuarioEncotrado: Usuario;

    let token = this.tokenService.getToken()
    let decoded = jwt_decode(token);
    this.nombreUsuario = decoded;
    let nombreUsuario = this.nombreUsuario.sub;

    listaUsuarios?.forEach(function(usuario: Usuario){
      if(usuario.nombreUsuario == nombreUsuario){
        usuarioEncotrado = usuario;
      }
    }
    )
    this.usuario = usuarioEncotrado!;
    const usuarioObj = await this.usuarioService.getUsuario(this.usuario.id!).toPromise();
    this.usuario = usuarioObj!;

    this.reservas = usuarioObj?.reservas
  }

}
