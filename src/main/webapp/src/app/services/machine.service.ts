import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  private machineUrl = 'machine/';
  constructor(private http: HttpClient, ) { }

  fetchMachineStatusFromServer() {
    return this.http.get(environment.appUrl + this.machineUrl);
  }

  selectProduct(productId: any) {
    return this.http.get(environment.appUrl + this.machineUrl + 'selectproduct/' + productId);
  }

  insertCoin(coin: string) {
    return this.http.get(environment.appUrl + this.machineUrl + 'insertcoin/' + coin);
  }

  returnCoin() {
    return this.http.delete(environment.appUrl + this.machineUrl + 'returncoin/');
  }

  resetSession() {
    return this.http.delete(environment.appUrl + this.machineUrl + 'resetSession/');
  }

  serviceCoin(coinStock) {
    return this.http.post(environment.appUrl + this.machineUrl + 'fillCoin/', coinStock);
  }

  serveProduct(productStock: any) {
    return this.http.post(environment.appUrl + this.machineUrl + 'fillProduct/', productStock);
  }
}
