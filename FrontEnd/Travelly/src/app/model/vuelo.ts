import { Aeropuerto } from "./aeropuerto";
import { Avion } from "./avion";

export class Vuelo {
    id!: number;
    fechaYHoraArribo: Date;
    fechaYHoraPartida: Date;
    precio: number;
    avion: Avion;
    aeropuertoPartida: Aeropuerto;
    aeropuertoLlegada: Aeropuerto;
    
   

    constructor(fechaYHoraArribo: Date, fechaYHoraPartida: Date, precio: number, avion: Avion, aeropuertoPartida: Aeropuerto, aeropuertoLlegada: Aeropuerto) {
        this.fechaYHoraArribo=fechaYHoraArribo;
        this.fechaYHoraPartida=fechaYHoraPartida;
        this.precio=precio;
        this.avion=avion;
        this.aeropuertoPartida =aeropuertoPartida;
        this.aeropuertoLlegada = aeropuertoLlegada;
    }
}
