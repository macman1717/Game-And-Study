import { Component, input, Input } from '@angular/core';

@Component({
  standalone: true,
  selector: 'app-set',
  imports: [],
  templateUrl: './set.component.html',
  styleUrl: './set.component.scss'
})
export class SetComponent {
  @Input() setName!: string;
  @Input() owner!: string;
  @Input() termCount!: string;
}
