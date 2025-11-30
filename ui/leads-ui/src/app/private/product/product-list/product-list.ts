import { Component, signal, inject, OnInit } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Product } from '../../../constants/product';
import { ProductService } from '../../../services/product.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductAdd } from '../product-add/product-add';

@Component({
  selector: 'app-product-list',
  imports: [CurrencyPipe],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})

export class ProductList implements OnInit {
  private productService = inject(ProductService);
  private model = inject(NgbModal);
  products = signal<Product[]>([]);

  ngOnInit() {
    this.productService.getProducts().subscribe({
      next: (data) => {
        console.log('products list', data);

        this.products.update(() => data)
      },
      error: (err) => console.error('Error fetching products:', err)
    });
  }

  addProduct(product: Product, isEdit: boolean) {
    const modalRef = this.model.open(ProductAdd, { backdrop: 'static', size: 'lg' });
    if (product) {
      modalRef.componentInstance.product = signal<Product>(product);
    }
    modalRef.componentInstance.isEdit = signal<boolean>(isEdit);

    modalRef.result.then((result) => {
      if (result) {
        if (isEdit) {
          this.products.update((products) =>
            products.map((p) => (p.id === result.id ? result : p))
          );
        } else {
          this.products.update((products) => [...products, result]);
        }
      }
    });
  }
}
