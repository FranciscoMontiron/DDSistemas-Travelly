import { Reserva } from "./reserva";
import { Usuario } from "./usuario";

export class Pago {
    id?: number;
    fechaYHora: Date;
    monto: number;
    reserva: Reserva;
    constructor(fechaYHora: Date, monto: number, reserva: Reserva) {
        this.fechaYHora = fechaYHora;
        this.monto = monto;
        this.reserva = reserva;
    }
}
