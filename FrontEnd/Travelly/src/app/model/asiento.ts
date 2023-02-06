import { Pasajero } from "./pasajero";

export class Asiento {
    id?:number;
    estado: boolean;
    numero:number;
    clase:string;
    asientoColumna: string;
    pasajero: Pasajero;

    constructor(estado: boolean, numero: number, clase: string, asientoColumna: string, pasajero: Pasajero) {
        this.estado = estado;
        this.numero = numero;
        this.clase = clase;
        this.asientoColumna = asientoColumna;
        this.pasajero =pasajero;
    }
}
