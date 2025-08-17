import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {
  constructor() {}

  public intercept(request: HttpRequest<any>, next: HttpHandler) {
    /*************************************************************** */
    // TODO: remove following lines after API implementation completed
    const useRealTokenUrls = ['api/auth/me', 'api/rentals'];
    const noTokenUrls = ['api/auth/login', 'api/auth/register'];
    let token: string | null = 'jwt';

    if (useRealTokenUrls.some((url) => request.url.includes(url))) {
      token = localStorage.getItem('token');
    } else if (noTokenUrls.some((url) => request.url.includes(url))) {
      token = null;
    }
    /*************************************************************** */

    // const token = localStorage.getItem('token'); // Uncomment when API ready
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }
    return next.handle(request);
  }
}
