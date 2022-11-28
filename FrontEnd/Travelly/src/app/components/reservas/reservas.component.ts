import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reserva } from 'src/app/model/reserva';
import { Usuario } from 'src/app/model/usuario';
import { ReservasService } from 'src/app/service/reservas.service';
import { TokenService } from 'src/app/service/token.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent implements OnInit {

  reservas: Reserva[] = [];


  constructor(private tokenService: TokenService, private reservaService: ReservasService,private router:Router) { }

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
    this.reservaService.getList().subscribe(data => {this.reservas = data, console.log(data)});
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
          this.reservaService.delete(id).subscribe(data =>{
            this.cargar();
          }, err => {
            alert("No se puedo borrar la reserva");
          })
        }
        Swal.fire(
          'Borrardo!',
          'Su reserva a sido eliminada con exito',
          'success'
        )
      }
    })
  }

  recibo() : void {
    Swal.fire('Recibo no disponible momentaneamente')
  }



}
