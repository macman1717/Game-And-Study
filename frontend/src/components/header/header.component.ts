import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LayoutService } from '../../services/layout.service';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-header',
  imports: [NgClass, RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  constructor(protected layoutService: LayoutService) {
    
  }
}
