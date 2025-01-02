import { Component, Input } from '@angular/core';

@Component({
  standalone: true,
  selector: 'app-card',
  imports: [],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  @Input() term!: string;
  @Input() definition!: string;

  onClick() {
    
  }
}
