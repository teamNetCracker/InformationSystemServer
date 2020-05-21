import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { TrackListModule } from './track-list/track-list.module';

import { AppComponent } from './app.component';
import { GenreListComponent } from './genre-list/genre-list.component';
import {RouterModule} from '@angular/router';
import {TrackListEpicFactory} from './track-list/track-list.epic';
import {TrackListActions} from './track-list/track-list.action';
import {StateModule} from './state/state.module';
import {HttpClientModule} from '@angular/common/http';
import {GenreListEpicFactory} from './genre-list/genre-list.epic';
import {GenreListActions} from './genre-list/genre-list.actions';
import {GenreListModule} from './genre-list/genre-list.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    TrackListModule,
    GenreListModule,
    StateModule,
    HttpClientModule,
  ],
  providers: [TrackListEpicFactory, TrackListActions, GenreListEpicFactory, GenreListActions],
  bootstrap: [AppComponent]
})
export class AppModule { }
