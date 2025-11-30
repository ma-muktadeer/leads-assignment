import { Routes } from '@angular/router';

import { Login } from './public/login/login';
import { ProductList } from './private/product/product-list/product-list';
import { secureGuard } from './gaurds/secure-guard';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: ':module', loadChildren: ()=> routedComponents, canActivateChild: [secureGuard]},
    // { path: 'products', component: ProductList },
    // { path: 'products/add', component: ProductAdd },
    // { path: 'products/:id/edit', component: ProductAdd },
    { path: '', redirectTo: '/login', pathMatch: 'full' }
];

export const routedComponents: Routes = [
  {path: 'products', component: ProductList, pathMatch: 'full'}
]
