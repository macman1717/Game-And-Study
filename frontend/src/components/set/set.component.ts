import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

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
  @Input() termCount!: number;
  @Input() id!: string;

  constructor(private router: Router) { }
  onClick() {
    this.router.navigate(['/sets', this.id]);
  }
}
