import { Asiento } from "./asiento";

export class Avion {
    id?: number;
    cantidadAsientos: number;
    matricula: string;
    marca: string;
    asientos: Asiento[];

    constructor(cantidadAsientos: number, matricula: string, marca: string, asientos: Asiento[]) {
        this.cantidadAsientos=cantidadAsientos;
        this.matricula=matricula;
        this.marca=marca;
        this.asientos=asientos;
    }
}
