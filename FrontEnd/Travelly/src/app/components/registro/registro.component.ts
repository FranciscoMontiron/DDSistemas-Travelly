import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NuevoUsuario } from 'src/app/model/nuevo-usuario';
import { AuthService } from 'src/app/service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {
  
  nombre!: string;
  apellido!: string;
  correo!: string;
  nombreUsuario!: string;
  dni!: number;
  direccion!: string;
  password!: string;

  nuevoUsuario!: NuevoUsuario;

  constructor(private authService: AuthService, private router: Router){}
  
  ngOnInit(): void {}
  
  
  onRegister(): void {
    this.nuevoUsuario = new NuevoUsuario(this.nombre, this.apellido, this.correo, this.nombreUsuario,
      this.dni, this.direccion ,this.password);
    
    this.authService.nuevo(this.nuevoUsuario).subscribe(data => {console.log(data),Swal.fire({
      icon: 'success',
      title: 'Se ha registrado el usuario correctamente!',
      showConfirmButton: false,
      timer: 1500
    })},err => {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Algo salio mal! revise los campos nuevamente...',
      })
    });
    this.router.navigate(['']);
  }
}
