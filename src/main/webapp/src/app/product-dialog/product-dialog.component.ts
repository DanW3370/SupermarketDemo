import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {MachineService} from "../services/machine.service";

@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styleUrls: ['./product-dialog.component.scss']
})
export class ProductDialogComponent implements OnInit {

  @Input() productStock:any;
  @Output() serviceDone: EventEmitter<any> = new EventEmitter();

  constructor(public modal: NgbActiveModal, private machineService: MachineService) { }

  ngOnInit() {

  }

  serviceProduct() {

    const self = this;
    let productMap = {};
    this.productStock.forEach(product => {
      productMap[product.id] = product.quantity;
    })
    this.machineService.serveProduct(productMap).subscribe(result => {
        self.closeDialog();
        self.serviceDone.emit(result);
      },
      error => {
        console.error(error)
      });
  }

  closeDialog() {
    this.modal.dismiss();
  }
}
