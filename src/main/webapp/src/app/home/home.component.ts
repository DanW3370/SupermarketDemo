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
    completedOrders: any;

    constructor(private machineService: MachineService, private modalService: NgbModal, private productService: ProductService) {

    }

    ngOnInit() {
        this.products = [];
        this.completedOrders = [];
        this.order = {orderItems:[], totalPrice: 0, totalSave: 0, finalPrice: 0};
        this.cleanBasket();
        this.getAllProducts();
    }

    addToCart(productId) {
        const self = this;
        this.machineService.addToCart(productId).subscribe(
            order => {

                self.order = order;
            },
            this.errorHandler
        );
    }

    checkout() {
        const self = this;
        this.machineService.checkOut().subscribe(
            data => {
                let msg = 'id: ' + data['id'] + ' total price: ' + data['totalPrice'] + ' total save: ' + data['totalSave'] + ' final price: ' + data['finalPrice'];
                self.alert('Order Detail', msg);
                self.getOrderHistory();
                self.order = {orderItems:[], totalPrice: 0, totalSave: 0, finalPrice: 0};
            },
            this.errorHandler
        );
    }

  cleanBasket() {
    const self = this;
    this.machineService.cleanBasket().subscribe(
      data => {
        self.order = {orderItems:[], totalPrice: 0, totalSave: 0, finalPrice: 0};
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

    getOrderHistory() {
      const self = this;
      this.machineService.getOrderHistory().subscribe(data => {
        self.completedOrders = data;
      },
        this.errorHandler)
    }

    private getAllProducts() {
        this.productService.getAllProducts().subscribe(
            data => {
                this.products = data;
            },
            this.errorHandler
        );
    }
}
