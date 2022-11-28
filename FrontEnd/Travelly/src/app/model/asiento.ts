import { Pasajero } from "./pasajero";

export class Asiento {
    id?:number;
    estado: string;
    numero:number;
    clase:string;
    asientoColumna: string;
    pasajero: Pasajero;

    constructor(estado: string, numero: number, clase: string, asientoColumna: string, pasajero: Pasajero) {
        this.estado = estado;
        this.numero = numero;
        this.clase = clase;
        this.asientoColumna = asientoColumna;
        this.pasajero =pasajero;
    }
}
