import { Avion } from "./avion";
import { Pasajero } from "./pasajero";

export class Asiento {
    id?:number;
    estado: boolean;
    seleccionado: boolean;
    numero:number;
    clase:string;
    asientoColumna: string;
    pasajero: Pasajero;
    avion: Avion;

    constructor(estado: boolean, seleccionado: boolean ,numero: number, clase: string, asientoColumna: string, pasajero: Pasajero, avion: Avion) {
        this.estado = estado;
        this.seleccionado = seleccionado;
        this.numero = numero;
        this.clase = clase;
        this.asientoColumna = asientoColumna;
        this.pasajero = pasajero;
        this.avion = avion;
    }
}
