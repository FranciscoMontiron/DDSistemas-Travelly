import { Pais } from "./pais";

export class Aeropuerto {
    id? :number;
    codigo :string;
    latitud: string;
    longitud: string;
    nombre: string;
    region: string;
    pais: Pais;

    constructor(codigo:string, latitud:string, longitud:string, nombre:string, region:string, pais: Pais) {
        this.codigo = codigo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.region = region;
        this.pais = pais;
    }
}
