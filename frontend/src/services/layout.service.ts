import { Injectable } from '@angular/core';

interface LayoutState {
  profileSidebarVisible: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class LayoutService {

  state: LayoutState = {
    profileSidebarVisible: false,
  };

  constructor() { }

  showProfileSidebar() {
    this.state.profileSidebarVisible = !this.state.profileSidebarVisible;
    if (this.state.profileSidebarVisible) {
      //this.overlayOpen.next(null);
    }
  }
}
