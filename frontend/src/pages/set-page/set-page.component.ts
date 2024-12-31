import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-set-page',
  imports: [],
  templateUrl: './set-page.component.html',
  styleUrl: './set-page.component.scss'
})
export class SetPageComponent implements OnInit {
  setId!: string;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.setId = this.route.snapshot.paramMap.get('id')!;
  }
}
