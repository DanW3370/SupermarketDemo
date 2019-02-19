import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  private machineUrl = 'shoppingCart/';
  constructor(private http: HttpClient, ) { }

  checkOut() {
    return this.http.get(environment.appUrl + this.machineUrl + 'checkout/');
  }

  addToCart(productId: any) {
    return this.http.get(environment.appUrl + this.machineUrl + 'addProduct/' + productId);
  }

  cleanBasket() {
    return this.http.delete(environment.appUrl + this.machineUrl + 'cleanBasket/');
  }

  getOrderHistory() {
    return this.http.get(environment.appUrl + this.machineUrl + 'getOrderHistory/');
  }
}
