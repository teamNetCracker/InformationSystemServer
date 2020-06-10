import {NgModule} from '@angular/core';
import {TrackListComponent} from './track-list.component';
import {CommonModule} from '@angular/common';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [TrackListComponent],
  exports: [
    TrackListComponent
  ],
    imports: [
        CommonModule,
        FormsModule
    ]
})
export class TrackListModule {
}
