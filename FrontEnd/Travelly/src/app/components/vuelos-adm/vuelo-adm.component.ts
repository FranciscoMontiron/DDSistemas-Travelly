import { Component, OnInit } from '@angular/core';
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

  vuelos: Vuelo[] = [];

  constructor(private tokenService: TokenService, private vueloService: VueloService) { }

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

}
