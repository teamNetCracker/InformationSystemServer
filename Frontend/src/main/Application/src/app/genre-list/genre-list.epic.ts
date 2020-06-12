import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Epic, ofType} from 'redux-observable';
import {Action} from 'redux';
import {map, mergeMap} from 'rxjs/operators';
import {
  AddGenreAction,
  FindGenreAction,
  GenreListActions,
  RemoveGenreAction,
  UpdateGenreAction
} from './genre-list.actions';
import {Genre} from './genre-list.data';
import {
  AddTrackAction,
  FindTrackAction,
  RemoveTrackAction,
  TrackListActions,
  UpdateTrackAction
} from "../track-list/track-list.action";
import {Track} from "../track-list/track-list.data";



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
          this.http.get('rest/genre/getAllGenres')
            .pipe(map(loadedGenres => loadedGenres as Genre[]))
            .pipe(map(loadedGenres => {
              return this.genreListActions.setLoadedGenres(loadedGenres);
            }))
        )
      );
    };
  }
  addGenreEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(GenreListActions.ADD_GENRE),
        mergeMap((action:AddGenreAction) =>
          this.http.post('rest/genre/addGenre', action.genre)
            .pipe(map((response) => {
              return this.genreListActions.loadGenres();
            }))
        )
      );
    };
  }
  removeGenreEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(GenreListActions.REMOVE_GENRE),
        mergeMap((action:RemoveGenreAction) =>
          this.http.delete(`rest/genre/deleteGenre/${(action.genre.id)}`)
            .pipe(map((response:boolean) => {
              return this.genreListActions.loadGenres();
            }))
        )
      );
    };
  }
  searchGenreEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(GenreListActions.FIND_GENRE),
        mergeMap((action:FindGenreAction) =>
          this.http.get(`rest/genre/searchGenre/${(action.findName)}`)
            .pipe(map(loadedGenre => loadedGenre as Genre))
            .pipe(map(loadedGenre => {
              return this.genreListActions.setFindGenre(loadedGenre);
            }))
        )
      );
    };
  }
  updateGenreEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(GenreListActions.UPDATE_GENRE),
        mergeMap((action:UpdateGenreAction) =>
          this.http.put(`rest/genre/updateGenre/`, action.genre)
            .pipe(map((response:boolean) => {
              return this.genreListActions.loadGenres();
            }))
        )
      );
    };
  }
}


