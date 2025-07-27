import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';
import { AuthService } from '../../services/auth.service';
import { RegisterRequest } from '../../interfaces/registerRequest.interface';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';
import { User } from 'src/app/interfaces/user.interface';
import { RegisterError } from '../../interfaces/registerError.interface';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  public error: null | RegisterError = null;
  public onError = false;
  public unknownError = 'An error occurred';

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    name: ['', [Validators.required, Validators.minLength(3)]],
    password: ['', [Validators.required, Validators.minLength(3)]],
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe({
      next: (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);
          this.router.navigate(['/rentals']);
        });
      },
      error: (error: HttpErrorResponse) => {
        if (typeof error.error === 'string') {
          this.onError = true;
        } else {
          this.error = error.error;
          this.updateFormErrors();
        }
      },
    });
  }

  /**
   * Updates the form errors based on the error response from the server.
   */
  updateFormErrors() {
    if (this.error?.email) {
      this.form.get('email')?.setErrors({ backendError: this.error?.email });
    }
    if (this.error?.name) {
      this.form.get('name')?.setErrors({ backendError: this.error?.name });
    }
    if (this.error?.password) {
      this.form
        .get('password')
        ?.setErrors({ backendError: this.error?.password });
    }
  }
}
