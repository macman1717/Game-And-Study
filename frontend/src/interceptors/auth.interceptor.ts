import { HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { inject } from "@angular/core";
import { AuthService } from "../services/auth.service";

export function authInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn) {
    const authService = inject(AuthService);
    const authToken = inject(AuthService).getAuthToken();
    console.log("Got token", authToken);
    console.log(authToken);
    console.log(authService.platformId);
    
    const newReq = req.clone({
        headers: req.headers.append('Authorization', `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTZWxmIiwiaWF0IjoxNzM1NjIzMTAyLCJleHAiOjE3MzU3MDk1MDJ9.HsfTtONVfj0O1naTY1EeMgFIHxa2zJ-YYoUJOngrbcc`),
    });
    return next(newReq);
}