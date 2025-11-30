import { Component, signal, inject, OnInit } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { Product } from '../../../constants/product';
import { ProductService } from '../../../services/product.service';
import { NgbModal, NgbPagination } from '@ng-bootstrap/ng-bootstrap';
import { ProductAdd } from '../product-add/product-add';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';
import { FilterPipe } from './filter.pipe';

export interface Item {
  name: string;
  value: string;
}

@Component({
  selector: 'app-product-list',
  imports: [CurrencyPipe, FormsModule, FilterPipe],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})

export class ProductList implements OnInit {

  private productService = inject(ProductService);
  private readonly router = inject(Router);
  private model = inject(NgbModal);
  products = signal<Product[]>([]);
  searchText = signal<string>('');
  selectedItem = signal<Item>(null);
  readonly module = signal<string>('');
  page = 1;
  pageSize = 5;
  totalPages = signal<number>(0);
  itemList = signal<Item[]>([
    { name: 'Men\'s Clothing', value: `men's clothing` },
    { name: 'Women\'s Clothing', value: `women's clothing` },
    { name: 'Electronics', value: `electronics` },
    { name: 'Jewelry', value: `jewelry` },
  ]);
  isLoading = signal<boolean>(true);
  constructor() {
    const rootSnapshot: ActivatedRouteSnapshot = this.router.routerState.snapshot.root;
    let child = rootSnapshot.firstChild;
    if (child) {
      this.module.update(() => child.params['module']);
      console.log('Module param:', this.module());
    }
    else {
      this.router.navigate(['/login']);
      return;
    }

  }
  ngOnInit() {
    this.loadProducts();
  }

    goToPage(p: number) {
    if (p >= 1 && p <= this.totalPages()) {
      this.page = p;
    }
  }
  private loadProducts() {
    this.productService.getProducts().subscribe({
      next: (data) => {
        console.log('products list', data);
        this.products.update(() => data);
        this.isLoading.update(() => false);
      },
      error: (err) => {
        console.error('Error fetching products:', err)
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: err,
        });
      }
    });
  }
  select(item: Item) {
    if (this.selectedItem()?.value === item.value) {
      return;
    }
    this.isLoading.update(() => true);
    this.selectedItem.update(() => item);
    this.productService.getProductsByCategory(this.selectedItem().value).subscribe({
      next: (data) => {
        console.log('products list', data);

        this.products.update(() => data)
        this.isLoading.update(() => false);
      },
      error: (err) => console.error('Error fetching products:', err)
    });
  }
  addProduct(productInfo: Product, isEdit: boolean) {
    const product = JSON.parse(JSON.stringify(productInfo));
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

  deleteProduct(id: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You won\'t be able to revert this!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, cancel!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.productService.deleteProduct(id).subscribe({
          next: () => {
            this.removeProductFromList(id);
          },
          error: (err) => console.error('Error deleting product:', err)
        });
      }
    });
  }

  private removeProductFromList(id: number) {
    this.products.update((products) => products.filter((p) => p.id !== id));
  }
}
