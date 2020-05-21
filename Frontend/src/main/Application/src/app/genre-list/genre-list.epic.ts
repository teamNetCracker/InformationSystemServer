import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Epic, ofType} from 'redux-observable';
import {Action} from 'redux';
import {map, mergeMap} from 'rxjs/operators';
import {GenreListActions} from './genre-list.actions';
import {Genre} from './genre-list.data';

@Injectable()
export class GenreListEpicFactory {
  constructor(private http: HttpClient,
              private genreListActions: GenreListActions) {
  }

  createLoadGenresEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(GenreListActions.LOAD_GENRES),
        mergeMap(action =>
          this.http.get('server/genres')
            .pipe(map(loadedGenres => loadedGenres as Genre[]))
            .pipe(map(loadedGenres => {
              return this.genreListActions.setLoadedGenres(loadedGenres);
            }))
        )
      );
    };
  }
}
