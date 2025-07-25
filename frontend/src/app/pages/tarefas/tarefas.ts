import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TarefaService } from '../../services/tarefa.service';
import { Tarefa } from '../../models/tarefa.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-tarefas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './tarefas.html',
  styleUrls: ['./tarefas.scss'],
})
export class TarefasPage {
  tarefas: Tarefa[] = [];
  filtros = {
    id: '',
    titulo: '',
    responsavel: '',
    situacao: '',
    prioridade: '',
    deadlineAntes: ''
  };

  modalCriarAberto = false;
  novaTarefa: Partial<Tarefa> = {
    titulo: '',
    descricao: '',
    responsavel: '',
    situacao: 'EM_ANDAMENTO',
    prioridade: 'BAIXA',
    deadline: new Date().toISOString().split('T')[0]
  };

  constructor(
    private tarefaService: TarefaService,
    private authService: AuthService
  ) {}

  buscar(): void {
    const token = this.authService.getToken();

    if (!token) {
      this.tarefas = [];
      alert('Você precisa estar logado para visualizar as tarefas.');
      return;
    }

    this.tarefaService.listarTarefas(this.filtros).subscribe((data) => {
      this.tarefas = data;
    });
  }

  abrirModalCriar() {
    this.modalCriarAberto = true;
    this.novaTarefa = {
      titulo: '',
      descricao: '',
      responsavel: '',
      situacao: 'EM_ANDAMENTO'
    };
  }

  fecharModalCriar() {
    this.modalCriarAberto = false;
  }

  criarTarefa() {
    this.tarefaService.criarTarefa(this.novaTarefa).subscribe(() => {
      this.fecharModalCriar();
      this.buscar();
    });
  }

  modalEditarAberto = false;
  tarefaEditando: Partial<Tarefa> | null = null;

  abrirModalEditar(tarefa: Tarefa) {
    this.modalEditarAberto = true;
    // Cria uma cópia dos dados para edição
    this.tarefaEditando = { ...tarefa };
  }

  fecharModalEditar() {
    this.modalEditarAberto = false;
    this.tarefaEditando = null;
  }

  atualizarTarefa() {
    if (this.tarefaEditando && this.tarefaEditando.id) {
      this.tarefaService.atualizarTarefa(this.tarefaEditando.id, this.tarefaEditando).subscribe(() => {
        this.fecharModalEditar();
        this.buscar();
      });
    }
  }

  excluirTarefa(id: number) {
    if (confirm('Tem certeza que deseja excluir esta tarefa?')) {
      this.tarefaService.excluirTarefa(id).subscribe(() => {
        this.buscar();
      });
    }
  }

  concluirTarefa(id: number) {
    this.tarefaService.concluirTarefa(id).subscribe(() => {
      this.buscar();
    });
  }

  ngOnInit(): void {
    this.buscar();
  }
}
