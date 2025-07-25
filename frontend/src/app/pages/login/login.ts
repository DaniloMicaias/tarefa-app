import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

interface AuthResponse {
  token: string;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.scss'],
})
export class LoginPage {
  usuario = '';
  senha = '';
  erro = '';

  constructor(private auth: AuthService, private router: Router) {}

  login() {
  this.erro = '';
  this.auth.login(this.usuario, this.senha).subscribe({
    next: (res: AuthResponse) => {
      this.auth.setToken(res.token);
      this.router.navigate(['/tarefas']);
    },
    error: (err) => {
      console.error('Erro de login:', err);
      this.erro = 'Usuário ou senha inválidos';
    }
  });
}
}
