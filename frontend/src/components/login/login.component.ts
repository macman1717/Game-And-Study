import { Component } from '@angular/core';
import { Checkbox } from 'primeng/checkbox';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [Checkbox, PasswordModule, InputTextModule, FormsModule, CardModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})

export class LoginComponent {
  password = "";
  email = "";
}
