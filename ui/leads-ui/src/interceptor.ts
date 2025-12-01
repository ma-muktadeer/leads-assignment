import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { LoginService } from './app/services/login.service';

export const interceptor: HttpInterceptorFn = (req, next) => {

  console.log('HTTP Request:', req);

  const loginService = inject(LoginService);

  let xhr = req;

  if (req.url.includes('/api/public/') || req.url.includes('/api/fake-store')) {
    xhr = req.clone({
      withCredentials: false,
      setHeaders: {
        'Content-Type': 'application/json',
      },
    });
  }
  else {
    if (!req.headers.get('Authorization')) {
      xhr = req.clone({
        withCredentials: true,
        setHeaders: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${loginService.getToken()}`,
        },
      });
    }
    else {
      xhr = req.clone({
        withCredentials: true,
        setHeaders: {
          'Content-Type': 'application/json',
        },
      });
    }
  }

  return next(xhr).pipe(
    catchError((error: any) => {
      return throwError(() => error);
    })
  )
};
