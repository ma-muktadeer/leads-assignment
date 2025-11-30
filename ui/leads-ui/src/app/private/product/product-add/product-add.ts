import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Product } from '../../../constants/product';
import { ProductService } from '../../../services/product.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-product-add',
  imports: [FormsModule],
  templateUrl: './product-add.html',
  styleUrl: './product-add.css',
  changeDetection: ChangeDetectionStrategy.OnPush

})
export class ProductAdd {
  private readonly activeModal = inject(NgbActiveModal);
  private productService = inject(ProductService);

  isEdit = signal<boolean>(false);
  saving = signal<boolean>(false);

  product = signal<Product>({
    id: 0,
    title: '',
    price: 0,
    category: '',
    description: '',
    image: '',
    rating: { rate: 0, count: 0 }
  });

  ngOnInit() {

  }

  close(result?: any) {
    this.activeModal.close(result);
  }

  submit() {
    if (this.saving()) {
      return;
    }
    debugger;
    this.saving.update(() => true);
    if (this.isEdit()) {
      this.productService.updateProduct(this.product().id, this.product()).subscribe({
        next: () => this.close(this.product()),
        error: (err) => { console.error(err); this.saving.update(() => false); }
      });
    } else {
      this.productService.addProduct(this.product()).subscribe({
        next: (res: any) => this.close(res),
        error: (err) => { console.error(err); this.saving.update(() => false); }
      });
    }
  }

}
