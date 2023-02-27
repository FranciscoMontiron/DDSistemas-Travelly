import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-adm',
  templateUrl: './dialog-adm.component.html',
  styleUrls: ['./dialog-adm.component.css']
})
export class DialogADMComponent {

  fechaHoraArribo: any;
  fechaHoraPartida: any;
  precio: any;
  avionSelect: any;
  aviones: any;
  aeropuertoPartida: any;
  aeropuertoLlegada: any;
  aeropuertos: any;
  operacion: string ="";

  constructor(
    public dialogRef: MatDialogRef<DialogADMComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.aviones = data.aviones;
    this.aeropuertos = data.aeropuertos;
    this.operacion = data.operacion;
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

  mostarInfo(){
    console.log(this.fechaHoraArribo)
    console.log(this.fechaHoraPartida)
    console.log(this.precio)
    console.log(this.avionSelect)
    console.log(this.aeropuertoPartida)
    console.log(this.aeropuertoLlegada)
  }

}
