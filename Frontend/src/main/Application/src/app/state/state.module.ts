import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgRedux, NgReduxModule} from '@angular-redux/store';
import {AppState} from './app.types';
import {TrackListEpicFactory} from '../track-list/track-list.epic';
import {combineEpics, createEpicMiddleware} from 'redux-observable-es6-compat';
import {applyMiddleware, createStore} from 'redux';
import {appReducer} from './app.reducer';
import {GenreListEpicFactory} from '../genre-list/genre-list.epic';

@NgModule({
  imports: [
    NgReduxModule
  ]
})
export class StateModule {
  constructor(public ngRedux: NgRedux<AppState>, private trackEpicFactory: TrackListEpicFactory,
              private genreListEpicFactory: GenreListEpicFactory) {
    const epicMiddleware = createEpicMiddleware();

    const store = createStore(
      appReducer,
      {},
      applyMiddleware(epicMiddleware)
    );

    ngRedux.provideStore(store);
    epicMiddleware.run(combineEpics(
      this.trackEpicFactory.createLoadTracksEpic(),
      this.genreListEpicFactory.createLoadGenresEpic(),
      this.trackEpicFactory.searchTrackEpic(),
      this.trackEpicFactory.removeTrackEpic(),
      this.trackEpicFactory.addTrackEpic(),
      this.genreListEpicFactory.addGenreEpic(),
      this.genreListEpicFactory.removeGenreEpic(),
      this.genreListEpicFactory.searchGenreEpic(),
      this.trackEpicFactory.updateTrackEpic(),
      this.genreListEpicFactory.updateGenreEpic(),
      this.trackEpicFactory.LoadTracksOnChangeGenreEpic(),
      this.trackEpicFactory.LoadTracksOnDeleteGenreEpic()
    ))
    ;
  }
}
