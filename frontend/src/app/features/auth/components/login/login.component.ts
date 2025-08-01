import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';
import { LoginRequest } from '../../interfaces/loginRequest.interface';
import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  public hide = true;
  public onError = false;
  public badCredentials = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(3)]],
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  public submit(): void {
    this.onError = false;
    this.badCredentials = false;

    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);

        this.authService.me().subscribe({
          next: (user: User) => {
            this.sessionService.logIn(user);
            this.router.navigate(['/rentals']);
          },
          error: () => (this.onError = true),
        });
      },
      error: (error: HttpErrorResponse) => {
        if (error.status === 401) {
          this.badCredentials = true;
          this.form.get('email')?.setErrors({ invalid: true });
          this.form.get('password')?.setErrors({ invalid: true });
        } else {
          console.error('An error occurred:', error.message);
          this.onError = true;
        }
      },
    });
  }
}
