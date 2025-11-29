import { Routes } from '@angular/router';

import { Login } from './public/login/login';
import { ProductList } from './private/product/product-list/product-list';

export const routes: Routes = [
    { path: 'login', component: Login },
    { path: 'products', component: ProductList },
    { path: '', redirectTo: '/login', pathMatch: 'full' }
];
