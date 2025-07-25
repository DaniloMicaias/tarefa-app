import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tarefa } from '../models/tarefa.model';

@Injectable({ providedIn: 'root' })
export class TarefaService {
  private apiUrl = 'http://localhost:8080/tarefas';

  constructor(private http: HttpClient) {}

  listarTarefas(filtros: any): Observable<Tarefa[]> {
    let params = new HttpParams();
    Object.keys(filtros).forEach(key => {
      if (filtros[key]) {
        params = params.set(key, filtros[key]);
      }
    });
    return this.http.get<Tarefa[]>(this.apiUrl, { params });
  }

  criarTarefa(tarefa: Partial<Tarefa>): Observable<Tarefa> {
    return this.http.post<Tarefa>(this.apiUrl, tarefa);
  }

  atualizarTarefa(id: number, tarefa: Partial<Tarefa>) {
    return this.http.put<Tarefa>(`${this.apiUrl}/${id}`, tarefa);
  }

  excluirTarefa(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  concluirTarefa(id: number) {
    return this.http.put<Tarefa>(`${this.apiUrl}/${id}/concluir`, {});
  }
}
