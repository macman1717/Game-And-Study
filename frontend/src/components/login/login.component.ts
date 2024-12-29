import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})

export class LoginComponent {
  password = "";
  email = "";

  constructor(private authService: AuthService) { }
  onSubmit() {
    const authRequest = { username: this.email, password: this.password };
    this.authService.generateToken(authRequest).subscribe({
      next: (token) => {
        console.log('Token received:', token);
        localStorage.setItem('authToken', token);
      },
      error: (error) => {
        console.error('Error during login:', error);
      }
    });
  }
}
