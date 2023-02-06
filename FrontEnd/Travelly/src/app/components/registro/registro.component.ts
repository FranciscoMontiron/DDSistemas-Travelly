import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NuevoUsuario } from 'src/app/model/nuevo-usuario';
import { AuthService } from 'src/app/service/auth.service';

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
    
    this.authService.nuevo(this.nuevoUsuario).subscribe(data => {console.log(data)},err => {
      console.log("error al crear usuario");
    });
    this.router.navigate(['']);
  }
}
