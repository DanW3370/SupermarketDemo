import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl = 'http://localhost:8081/products';
  constructor(private http: HttpClient, ) { }

  getAllProducts() {
    return this.http.get(this.productUrl);
  }
}
