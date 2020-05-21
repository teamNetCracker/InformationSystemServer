import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TrackListActions} from './track-list.action';
import {Epic, ofType} from 'redux-observable';
import {Action} from 'redux';
import {map, mergeMap} from 'rxjs/operators';
import {Track} from './track-list.data';

@Injectable()
export class TrackListEpicFactory {
  constructor(private http: HttpClient,
              private trackListActions: TrackListActions) {
  }

  createLoadTracksEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(TrackListActions.LOAD_TRACKS),
        mergeMap(action =>
          this.http.get('server/tracks')
            .pipe(map(loadedTracks => loadedTracks as Track[]))
            .pipe(map(loadedTracks => {
              return this.trackListActions.setLoadedTracks(loadedTracks);
            }))
        )
      );
    };
  }
}
