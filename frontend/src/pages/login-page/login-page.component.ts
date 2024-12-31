import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { LoginComponent } from "../../components/login/login.component";

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [HeaderComponent, LoginComponent],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {
  
}
