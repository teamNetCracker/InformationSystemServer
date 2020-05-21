import {NgModule} from '@angular/core';
import {TrackListComponent} from './track-list.component';
import {CommonModule} from '@angular/common';

@NgModule({
  declarations: [TrackListComponent],
  exports: [
    TrackListComponent
  ],
  imports: [
    CommonModule
  ]
})
export class TrackListModule {
}
