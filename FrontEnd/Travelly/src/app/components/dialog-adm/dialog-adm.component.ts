import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Vuelo } from 'src/app/model/vuelo';
import { VueloService } from 'src/app/service/vuelo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-dialog-adm',
  templateUrl: './dialog-adm.component.html',
  styleUrls: ['./dialog-adm.component.css']
})
export class DialogADMComponent {

  fechaArribo: any;
  horaArribo: any;

  fechaPartida: any;
  horaPartida: any;

  precio: any;
  avionSelect: any;
  aviones: any;
  aeropuertoPartida: any;
  aeropuertoLlegada: any;
  aeropuertos: any;
  operacion: string ="";
  vuelo: any;

  constructor(
    public dialogRef: MatDialogRef<DialogADMComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private vueloService: VueloService
  ) {
    this.aviones = data.aviones;
    this.aeropuertos = data.aeropuertos;
    this.operacion = data.operacion;
    this.vuelo = data.vuelo;
    if (data.operacion == 'editar'){
      this.vueloService.getVuelo(data.vuelo.id).subscribe((data)=>{this.precio=data.precio, this.avionSelect=data.avion, this.aeropuertoPartida = data.aeropuertoPartida, this.aeropuertoLlegada = data.aeropuertoLlegada})
    }
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

  editar():void{
    let fechaYHoraArribo = this.fechaArribo + ' ' + this.horaArribo + ':00';
    let fechaYHoraPartida = this.fechaPartida + ' ' + this.horaPartida + ':00';
    let vueloEdit = new Vuelo(fechaYHoraArribo, fechaYHoraPartida,this.precio,this.avionSelect,this.aeropuertoPartida,this.aeropuertoLlegada);

    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success m-3 px-3',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: 'Estas Seguro?',
      text: "Este cambio sera permanente",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si  ',
      cancelButtonText: 'No',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.vueloService.update(this.vuelo.id, vueloEdit).subscribe();
        window.location.reload();
        swalWithBootstrapButtons.fire(
          'Actualizado!',
          'El vuelo se a modificado correctamente',
          'success'
        )
      } else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cacelado',
          'El vuelo no ha sido modificado',
          'error'
        )
      }
    })
  }

  crear():void{
    let fechaYHoraArribo = this.fechaArribo + ' ' + this.horaArribo + ':00';
    let fechaYHoraPartida = this.fechaPartida + ' ' + this.horaPartida + ':00';
    let nuevoVuelo = new Vuelo(fechaYHoraArribo, fechaYHoraPartida,this.precio,this.avionSelect,this.aeropuertoPartida,this.aeropuertoLlegada);
    this.vueloService.save(nuevoVuelo).subscribe(data => {console.log(data);});
  }

}
