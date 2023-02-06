import { ThisReceiver } from "@angular/compiler";

export class NuevoUsuario {
    nombre!: string;
    apellido!: string;
    correo!: string;
    nombreUsuario!: string;
    dni!: number;
    direccion!: string;
    password!: string;
    constructor(nombre: string, apellido: string, correo: string, nombreUsuario: string, dni: number,
         direccion: string, password: string){
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=nombreUsuario;
        this.nombreUsuario=nombreUsuario;
        this.dni = dni;
        this.direccion=direccion;
        this.password=password;            
    }
}
