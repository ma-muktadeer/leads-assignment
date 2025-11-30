import { Component, inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Session } from '../../services/session';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private readonly loginService = inject(LoginService);
  private readonly router = inject(Router);
  loginForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      loginSource: ['fake', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.log('Login data:', this.loginForm.value);
      // Handle login logic here
      const res = this.loginService.login2FakeUrl(
        this.loginForm.value.username,
        this.loginForm.value.password
      );
      res.subscribe({
        next: (response) => {
          const token = response['token'];
          const loginSource = this.loginForm.get('loginSource')?.value;
          console.log('Login successful:', token);
          this.loginService.saveSession(token, loginSource);
          this.router.navigate([`${loginSource}/products`]);
        },
        error: (error) => {
          console.error('Login failed:', error);
          Swal.fire({
            icon: 'error',
            title: 'Login Failed',
            text: error.error || 'Invalid username or password. Please try again.'
          });
        }
      });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }
}
