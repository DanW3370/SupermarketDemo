import {Component, OnInit} from '@angular/core';
import {MachineService} from '../services/machine.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AlertDialogComponent} from '../alert-dialog/alert-dialog.component';
import {ProductService} from "../services/product.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    products: any;
    order: any;
    constructor(private machineService: MachineService, private modalService: NgbModal, private productService: ProductService) {
    }

    ngOnInit() {
        this.machineService.resetSession().subscribe(r => { }, this.errorHandler);
        this.products = [];
        this.getAllProducts();
    }

    selectProduct(productId) {
        const self = this;
        this.machineService.selectProduct(productId).subscribe(
            order => {

                self.processOrder(order);
            },
            this.errorHandler
        );
    }

    insertCoin(coin) {
        const self = this;
        this.machineService.insertCoin(coin).subscribe(
            order => {
                self.processOrder(order);
            },
            this.errorHandler
        );
    }

    returnCoin() {
        const self = this;
        this.machineService.returnCoin().subscribe(
            data => {
                let msg = '';
                // tslint:disable-next-line:forin
                for (const key in data) {
                    msg += 'Coin ' + Number(key).toFixed(2) + ': ' + data[key] + '\t ';
                }
                self.alert('Return coin', msg);
                self.getAllProducts();
            },
            this.errorHandler
        );
    }

    errorHandler(error) {
        console.error(error);
    }

    alert(title, message) {
      const alert = this.modalService.open(AlertDialogComponent);
      alert.componentInstance.title = title;
      alert.componentInstance.message = message;
    }

    private processOrder(order: Object) {
        if (order && order['id'] && order['id'] > 0) {
            this.alert('You have purchase '
              + order['product'].name, 'Inserted Coin: ' + order['totalPay'] + ' product price'
              + order['productPrice'] + ' Change:' + Number(order['change']).toFixed(2));
            this.machineService.resetSession();
            this.getAllProducts();
        } else {
            this.order = order;
        }
    }

    private getAllProducts() {
        this.productService.getAllProducts().subscribe(
            data => {
                this.products = data;
            },
            this.errorHandler
        );

        this.order = { totalPay: 0.0, productPrice: 0.0, change: 0.0 };
    }

    disableReturnCoin() {
        let r = true;
        if (this.order.totalPay > 0) {
            r = false;
        }
        return r;
    }

    disabledSelection(product: any) {
        let r = true;
        if (this.order && product.price <= this.order.totalPay) {
            r = false;
        }
        return r;
    }
}
