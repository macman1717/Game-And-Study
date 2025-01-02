import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SidebarComponent } from "../../components/sidebar/sidebar.component";
import { Card } from '../../models/set.model';
import { Set } from '../../models/set.model';
import { SetService } from '../../services/set.service';
import { CardComponent } from "../../components/card/card.component";

@Component({
  standalone: true,
  selector: 'app-set-page',
  imports: [SidebarComponent, CardComponent],
  templateUrl: './set-page.component.html',
  styleUrl: './set-page.component.scss'
})
export class SetPageComponent implements OnInit {
  setId!: string;
  set!: Set;

  constructor(private route: ActivatedRoute, private setService: SetService) {}

  ngOnInit() {
    this.setId = this.route.snapshot.paramMap.get('id')!;
    this.setService.getSetDetails(this.setId).subscribe(
      (data) => {
        this.set = data;
      }
    );
  }
}
