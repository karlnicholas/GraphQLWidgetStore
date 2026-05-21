import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

interface Widget {
  id: string;
  name: string;
  price: number;
  imageUrl: string;
}

@Component({
  selector: 'app-widget-list',
  standalone: true,
  imports: [CommonModule], // Required for *ngFor and the async pipe
  template: `
    <div class="widget-grid">
      <div *ngFor="let widget of widgets$ | async" class="widget-card">
        <img [src]="widget.imageUrl" [alt]="widget.name" loading="lazy" />
        <h3>{{ widget.name }}</h3>
        <p>\${{ widget.price }}</p>
      </div>
    </div>
  `,
  styles: [`
    .widget-grid { display: flex; gap: 20px; flex-wrap: wrap; padding: 20px; }
    .widget-card { border: 1px solid #ccc; border-radius: 8px; padding: 10px; width: 200px; text-align: center; box-shadow: 2px 2px 8px rgba(0,0,0,0.1); }
    .widget-card img { max-width: 100%; height: auto; border-radius: 4px; }
  `]
})
export class WidgetListComponent implements OnInit {
  widgets$!: Observable<Widget[]>;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    const query = {
      query: `
        query {
          widgets {
            id
            name
            price
            imageUrl
          }
        }
      `
    };

    // This POST request is intercepted by your proxy.conf.json and sent to Spring Boot
    this.widgets$ = this.http.post<any>('/graphql', query).pipe(
      map(response => response.data.widgets)
    );
  }
}
