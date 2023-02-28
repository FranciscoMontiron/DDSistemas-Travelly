import { Aeropuerto } from "./aeropuerto";
import { Avion } from "./avion";

export class Vuelo {
    id!: number;
    fechaYHoraArribo: string;
    fechaYHoraPartida: string;
    precio: number;
    avion: Avion;
    aeropuertoPartida: Aeropuerto;
    aeropuertoLlegada: Aeropuerto;
    
   

    constructor(fechaYHoraArribo: string, fechaYHoraPartida: string, precio: number, avion: Avion, aeropuertoPartida: Aeropuerto, aeropuertoLlegada: Aeropuerto) {
        this.fechaYHoraArribo=fechaYHoraArribo;
        this.fechaYHoraPartida=fechaYHoraPartida;
        this.precio=precio;
        this.avion=avion;
        this.aeropuertoPartida =aeropuertoPartida;
        this.aeropuertoLlegada = aeropuertoLlegada;
    }
}
