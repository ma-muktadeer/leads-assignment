import { CanActivateFn, Router } from '@angular/router';
import { Session } from '../services/session';
import { inject } from '@angular/core';

export const secureGuard: CanActivateFn = (route, state) => {
  const session = inject(Session);
  const router = inject(Router);

  if (!session.getItem('AUTH_TOKEN') || !session.getItem('LOGIN_SOURCE') || session.getItem('LOGIN_SOURCE') !== route.params['module']) {
    session.clear();
    return router.createUrlTree(['/login']);
  }

  return true;
};
