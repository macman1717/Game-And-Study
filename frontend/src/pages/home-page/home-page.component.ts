import { Component } from '@angular/core';
import { SetComponent } from '../../components/set/set.component';
import { Set } from '../../models/set.model';
import { SetService } from '../../services/set.service';
import { error } from 'console';

@Component({
  selector: 'app-home-page',
  imports: [SetComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent {
  sets: Set[] = [];

  constructor(private setService: SetService) {}
  
  ngOnInit(): void {
    console.log("Making subscription");
    this.setService.getSets().subscribe(
      (data) => {
        console.log("Got sets");
        this.sets = data;
      },
      (error) => {
        console.error("Couldn't fetch sets:", error);
      }
    );
  }
}
