import {NgModule} from '@angular/core';
import {GenreListComponent} from './genre-list.component';
import {CommonModule} from '@angular/common';

@NgModule({
  declarations: [GenreListComponent],
  exports: [
    GenreListComponent
  ],
  imports: [
    CommonModule
  ]
})
export class GenreListModule {
}
