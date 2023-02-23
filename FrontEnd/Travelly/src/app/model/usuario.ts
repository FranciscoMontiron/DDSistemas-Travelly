import { Pago } from "./pago";
import { Reserva } from "./reserva";

export class Usuario {
    id?: number;
    nombre : string;
    apellido: string;
    correo: string;
    nombreUsuario: string;
    dni: number;
    direccion: string;
    password: string;
    roles: string[];
    reservas: Reserva;


    constructor(nombre: string,apellido: string, correo: string, nombreUsuario: string, dni: number, direccion: string, password: string, reservas: Reserva, roles: string[]) {
        this.nombre = nombre;
        this.apellido =apellido;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.dni = dni;
        this.direccion = direccion;
        this.password = password;
        this.reservas = reservas;
        this.roles = roles;
    }
}
