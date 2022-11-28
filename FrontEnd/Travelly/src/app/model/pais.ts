export class Pais {
    id?: number;
    codigoPais: number;
    nombre: string;

    constructor(codigoPais: number, nombre: string){
        this.codigoPais = codigoPais;
        this.nombre = nombre;
    }
}
