import { HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { inject } from "@angular/core";
import { AuthService } from "../services/auth.service";

export function authInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn) {
    const excludedUrls = ['/auth/generateToken'];
    if (excludedUrls.some(url => req.url.includes(url))) {
        return next(req);
    }
    
    const authToken = inject(AuthService).getAuthToken();
    const newReq = req.clone({
        headers: req.headers.append('Authorization', `Bearer ${authToken}`),
    });
    return next(newReq);
}