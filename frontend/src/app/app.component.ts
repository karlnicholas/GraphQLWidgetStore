import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { WidgetListComponent } from './widget-list/widget-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, WidgetListComponent], // Add it here
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
