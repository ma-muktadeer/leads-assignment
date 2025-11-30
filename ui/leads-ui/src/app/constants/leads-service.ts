import { Observable } from "rxjs";
import { Product } from "./product";

export interface LeadsService {
  getProducts(): Observable<Product[]>;
  getProduct(id: number): Observable<Product>;
  addProduct(product: Product): Observable<Product>;
  updateProduct(id: number, product: Product): Observable<Product>;
  deleteProduct(id: number): Observable<void>;
  getProductsByCategory(category: string): Observable<Product[]>;
}
