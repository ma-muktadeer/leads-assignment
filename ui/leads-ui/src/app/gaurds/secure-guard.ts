import { CanActivateFn, Router } from '@angular/router';
import { Session } from '../services/session';
import { inject } from '@angular/core';

export const secureGuard: CanActivateFn = (route, state) => {
  const session = inject(Session);
  const router = inject(Router);
  if (!session.getItem('AUTH_TOKEN') || !session.getItem('LOGIN_SOURCE')) {
    session.clear();
    return router.createUrlTree(['/login']);
  }
  const loginSource = session.getItem('LOGIN_SOURCE');
  const module = route.params['module'];
  if (loginSource !== module) {
    const url = state.url;
    return router.createUrlTree([url.replaceAll(`/${module}/`, `/${loginSource}/`)]);
  }

  return true;
};
