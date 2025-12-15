import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";
import { AppUserService, AppUser } from "../../core/services/app-user.service";
import { CookieService } from "ngx-cookie-service";
import Swal from "sweetalert2";

@Component({
  selector: "app-signup",
  templateUrl: "./signup.html",
  styleUrls: ["./signup.css"],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
})
export class Signup implements OnInit {
  signupForm!: FormGroup;
  successMessage: string = "";
  errorMessage: string = "";

  constructor(
    private fb: FormBuilder,
    private userService: AppUserService,
    private cookieService: CookieService
  ) {}

  ngOnInit() {
    this.signupForm = this.fb.group(
      {
        firstName: ["", [Validators.required, Validators.minLength(2)]],
        lastName: ["", [Validators.required, Validators.minLength(2)]],
        email: ["", [Validators.required, Validators.email]],
        bio: ["", Validators.required],
        location: ["", Validators.required],
        password: ["", [Validators.required, Validators.minLength(6)]],
        confirmPassword: ["", Validators.required],
      },
      { validators: this.passwordMatchValidator }
    );
  }

  passwordMatchValidator(form: FormGroup) {
    const pass = form.get("password")?.value;
    const confirm = form.get("confirmPassword")?.value;
    return pass === confirm ? null : { passwordMismatch: true };
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      Object.keys(this.signupForm.controls).forEach((key) => {
        const control = this.signupForm.get(key);
        if (control?.invalid) {
          control.markAsTouched();
        }
      });
      return;
    }

    const newUser: AppUser = {
      firstName: this.signupForm.value.firstName,
      lastName: this.signupForm.value.lastName,
      email: this.signupForm.value.email,
      password: this.signupForm.value.password,
      bio: this.signupForm.value.bio,
      location: this.signupForm.value.location,
      userType: "USER",
    };

    this.userService.create(newUser).subscribe({
      next: (res) => {
        const { password, ...safeUser } = res;

        this.cookieService.set(
          "connected_user",
          JSON.stringify(safeUser),
          1, // durée 1 jour, ou plus si "remember me"
          "/"
        );

        this.successMessage = "Inscription réussie ! Bienvenue 🎉";
        this.errorMessage = "";
        this.signupForm.reset();

        Swal.fire({
          title: "Inscription réussie !",
          text: `Bienvenue ${safeUser.firstName} !`,
          icon: "success",
        });
      },
      error: (err) => {
        this.errorMessage =
          "Une erreur est survenue lors de l'inscription.";
        this.successMessage = "";
        console.error(err);
      },
    });
  }
}
