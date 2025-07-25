import { Routes } from '@angular/router';
import { TarefasPage } from './pages/tarefas/tarefas';

import { LoginPage } from './pages/login/login';

export const routes: Routes = [
  {
    path: 'auth/login',
    component: LoginPage,
  },
  {
    path: 'tarefas',
    component: TarefasPage,
  },
  {
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full',
  }
];
