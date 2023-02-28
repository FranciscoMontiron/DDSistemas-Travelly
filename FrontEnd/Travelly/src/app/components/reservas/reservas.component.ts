import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reserva } from 'src/app/model/reserva';
import { Usuario } from 'src/app/model/usuario';
import { ReservasService } from 'src/app/service/reservas.service';
import { TokenService } from 'src/app/service/token.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import Swal from 'sweetalert2';
import jwt_decode from 'jwt-decode';
import { PagoService } from 'src/app/service/pago.service';
import { Pago } from 'src/app/model/pago';
import { MatDialog } from '@angular/material/dialog';
import { DialogADMComponent } from '../dialog-adm/dialog-adm.component';
import { DialogPagoComponent } from '../dialog-pago/dialog-pago.component';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent implements OnInit {

  reservas!: any;
  usuario: any;
  nombreUsuario: any;
  pago: any;

  dialogResult: any;
  

  constructor(private tokenService: TokenService, private reservaService: ReservasService,
              private router:Router, private usuarioService: UsuarioService, private pagoService : PagoService,
              public dialog: MatDialog
              ) { }

  isLogged = false;

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.obtenerReservasDelUsuario();
    } else {
      this.isLogged = false;
    }
  }

  abrirDialogo(reserva: Reserva) {
    const dialogRef = this.dialog.open(DialogPagoComponent, {
      data: {
        reserva: reserva,
        usuario: this.usuario
      }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      this.dialogResult =  result;
      this.router.navigate(['reservas']);
      window.location.reload();
    });
  }

  cancelar(reserva: any): void {
    
    let fechaActual = new Date();
    if(fechaActual >= reserva.fechaYHora){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'La reserva a expiro!',
      })
        
    }else{
    
    Swal.fire({
      title: '¿Estas seguro?',
      text: "Este cambio no se puede revertir, las devoluciones tardan 30 dias habiles",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si!',
      cancelButtonText: 'No!'
    }).then((result) => {
      if (result.isConfirmed) {
        if( reserva.id!= undefined){
          let reservaModi = new Reserva('cancelada',fechaActual,reserva.montoFinal,this.usuario,reserva.vuelo);
          this.reservaService.update(reserva.id, reservaModi).subscribe(data => {
            this.obtenerReservasDelUsuario();
          }, err => {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Algo salio mal vuelve a intentarlo mas tarde!',
            })
          })
        }
        Swal.fire(
          'Borrardo!',
          'Su reserva a sido eliminada con exito, El pago se reembolsara en 30 dias habiles...',
          'success'
        )
      }
    })
  }
  }

  pagar(reserva: Reserva):void   {
      let fechaActual = new Date();

      const pago = new Pago(fechaActual,reserva.montoFinal,reserva);
      this.pagoService.crearPago(pago).subscribe(data => {this.pago = data});

      let reservaModi = new Reserva('pago',fechaActual,reserva.montoFinal,this.usuario,reserva.vuelo);
      this.reservaService.update(reserva.id!, reservaModi).subscribe(data => {});
  }

  async obtenerReservasDelUsuario(): Promise<void>{

    const listaUsuarios  = await this.usuarioService.getList().toPromise();
    let usuarioEncotrado: Usuario;

    let token = this.tokenService.getToken()
    let decoded = jwt_decode(token);
    this.nombreUsuario = decoded;
    let nombreUsuario = this.nombreUsuario.sub;

    listaUsuarios?.forEach(function(usuario: Usuario){
      if(usuario.nombreUsuario == nombreUsuario){
        usuarioEncotrado = usuario;
      }
    }
    )
    this.usuario = usuarioEncotrado!;
    const usuarioObj = await this.usuarioService.getUsuario(this.usuario.id!).toPromise();
    this.usuario = usuarioObj!;

    this.reservas = usuarioObj?.reservas
  }

  obtenerPagoPorReservaId(reserva : Reserva){
    this.pagoService.traerPagoDeReserva(reserva.id!).subscribe((data)=>{console.log(data);})
  }

}
