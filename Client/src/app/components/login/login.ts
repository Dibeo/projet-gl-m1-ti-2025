import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AppUserService, AppUser } from '../../core/services/app-user.service';
import { CookieService } from 'ngx-cookie-service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit {
  loginForm!: FormGroup;
  isLoading = false;
  showPassword = false;

  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private userService: AppUserService,
    private cookieService: CookieService // ✅ injecté
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      rememberMe: [false],
    });
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      Object.keys(this.loginForm.controls).forEach((key) => {
        this.loginForm.get(key)?.markAsTouched();
      });
      return;
    }

    this.isLoading = true;

    const { email, password, rememberMe } = this.loginForm.value;

    this.userService.getAll().subscribe({
      next: (users: AppUser[]) => {
        this.isLoading = false;

        const user = users.find((u) => u.email === email && u.password === password);

        if (!user) {
          this.errorMessage = 'Email ou mot de passe incorrect.';
          this.successMessage = '';

          Swal.fire({
            title: 'Erreur',
            text: 'Identifiants incorrects',
            icon: 'error',
          });
          return;
        }

        const { password: _, ...safeUser } = user;
        const expireDays = rememberMe ? 7 : 1;

        this.cookieService.set('connected_user', JSON.stringify(safeUser), expireDays, '/');

        this.successMessage = 'Connexion réussie ! Bienvenue 😊';
        this.errorMessage = '';

        Swal.fire({
          title: 'Connecté 🎉',
          text: 'Vous êtes maintenant connecté.',
          icon: 'success',
        });

        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = 'Erreur lors de la connexion.';
        this.successMessage = '';

        Swal.fire({
          title: 'Erreur serveur',
          text: 'Impossible de récupérer les utilisateurs.',
          icon: 'error',
        });

        console.error(err);
      },
    });
  }

  forgotPassword(): void {
    this.router.navigate(['/forgot-password']);
  }

  register(): void {
    this.router.navigate(['/register']);
  }
}
