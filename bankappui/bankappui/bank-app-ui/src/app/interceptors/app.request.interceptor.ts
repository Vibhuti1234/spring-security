import { Injectable } from '@angular/core';
import { HttpInterceptor,HttpRequest,HttpHandler,HttpErrorResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import {Router} from '@angular/router';
import {tap} from 'rxjs/operators';
import { User } from 'src/app/model/user.model';
import { Observable } from 'rxjs';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  user = new User();
  constructor(private router: Router) {}
  

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let httpHeaders = new HttpHeaders();
    this.user = JSON.parse(sessionStorage.getItem('userdetails'));
    if(this.user && this.user.password && this.user.email){
      httpHeaders = httpHeaders.append('Authorization', 'Basic ' + btoa(this.user.email + ':' + this.user.password));
    }
    
    httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');
    let xhr = req.clone({
      headers: httpHeaders
    });
    let authorization=sessionStorage.getItem("Authorization");
    if(authorization){
      xhr=req.clone({ setHeaders: { "Authorization": authorization } });
    }
    // for csrf enabled in backend but currently I have disabled it so no need to implement this code .i.e. setting this header.
    // let xsrf=sessionStorage.getItem('XSRF-TOKEN');
    // if(xsrf){
    //   xhr=req.clone({ setHeaders: { "X-XSRF-TOKEN": xsrf } });
    // }
    return next.handle(xhr).pipe(tap(() => { },
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 401) {
            return;
          }
          this.router.navigate(['dashboard']);
        }
      }));
  }
}