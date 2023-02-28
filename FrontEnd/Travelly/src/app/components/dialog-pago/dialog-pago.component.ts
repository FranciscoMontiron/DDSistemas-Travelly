import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Pago } from 'src/app/model/pago';
import { Reserva } from 'src/app/model/reserva';
import { PagoService } from 'src/app/service/pago.service';
import { ReservasService } from 'src/app/service/reservas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-dialog-pago',
  templateUrl: './dialog-pago.component.html',
  styleUrls: ['./dialog-pago.component.css']
})
export class DialogPagoComponent {

  reserva: any;
  usuario: any;

  metodoDePago: any;

  nomTitular: string = '';
  numeroDeTarjeta: string = '';
  fechaDeCaducidad: string = '';
  codigoDeSeguridad: string='';
  cuotas: number = 1;
  
  pago! : any;
  

  constructor(public dialogRef: MatDialogRef<DialogPagoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private pagoService: PagoService,private reservaService: ReservasService) { 
      this.reserva = data.reserva;
      this.usuario = data.usuario;
    }

  onCancelClick(): void {
    this.dialogRef.close();
    
  }

  pagar(){
    let fechaActual = new Date();

      const pago = new Pago(fechaActual,this.reserva.montoFinal,this.reserva);
      this.pagoService.crearPago(pago).subscribe(data => {this.pago = data});

      let reservaModi = new Reserva('pago',fechaActual,this.reserva.montoFinal,this.usuario,this.reserva.vuelo);
      this.reservaService.update(this.reserva.id!, reservaModi).subscribe(data => {});

      Swal.fire(
        'Pago realizado!',
        'Operacion realizada con exito!',
        'success'
      );
  }



}
