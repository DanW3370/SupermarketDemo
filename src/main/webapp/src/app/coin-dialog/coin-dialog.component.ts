import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {MachineService} from "../services/machine.service";

@Component({
  selector: 'app-coin-dialog',
  templateUrl: './coin-dialog.component.html',
  styleUrls: ['./coin-dialog.component.scss']
})
export class CoinDialogComponent implements OnInit {

  constructor(public modal: NgbActiveModal, private machineService: MachineService) { }

  @Output() serviceDone: EventEmitter<any> = new EventEmitter();

  coin5 = 0;
  coin10 = 0;
  coin25 = 0;
  coin100 = 0;

  ngOnInit() {

  }

  serviceCoin() {
    const self = this;
    const coinStock = {0.05: this.coin5, 0.10: this.coin10, 0.25: this.coin25, 1.00: this.coin100};
    this.machineService.serviceCoin(coinStock).subscribe(result => {
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
