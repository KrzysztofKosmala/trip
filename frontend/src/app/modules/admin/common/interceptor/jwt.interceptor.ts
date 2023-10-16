import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { JwtService } from "src/app/modules/common/service/jwt.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor
{
    constructor(private jwtService: JwtService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>
    {
        let token = this.jwtService.getToken();console.log(req.url)
        if(token && 
            (
                 req.url.startsWith("/api/v1/admin")
                 || req.url.startsWith("/api/v1/images")
                 || req.url.startsWith("/api/v1/orders")
                 || req.url.startsWith("/api/v1/profiles")
                 || req.url.startsWith("/api/v1/users")
                 || req.url.startsWith("/api/v1/rooms")
            )
        )
        {
            console.log("sending token " + token)
            req = req.clone(
                {
                    headers: req.headers.set("Authorization", "Bearer " + token)
                }
            );
        }
        return next.handle(req);
    }
}