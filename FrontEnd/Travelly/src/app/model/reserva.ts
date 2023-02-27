import { LoginUsuario } from "./login-usuario";
import { Pago } from "./pago";
import { Usuario } from "./usuario";
import { Vuelo } from "./vuelo";

export class Reserva {
    
    id?: number;
    estado: string;
    fechaYHora: Date;
    montoFinal: number;
    usuario: Usuario;
    vuelo: Vuelo;

    constructor(estado: string, fechaYHora: Date,montoFinal: number ,usuario: Usuario, vuelo: Vuelo){
        this.estado = estado;
        this.fechaYHora = fechaYHora;
        this.montoFinal = montoFinal;
        this.usuario = usuario;
        this.vuelo = vuelo;
    }
    
}
