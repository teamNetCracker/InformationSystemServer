import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FindTrackAction, RemoveTrackAction, TrackListActions} from './track-list.action';
import {Epic, ofType} from 'redux-observable';
import {Action} from 'redux';
import {catchError, map, mergeMap, switchMap} from 'rxjs/operators';
import {Track} from './track-list.data';
import {pipe} from "rxjs";

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
          this.http.get('rest/track/getAllTracks')
            .pipe(map(loadedTracks => loadedTracks as Track[]))
            .pipe(map(loadedTracks => {
              console.log(action.type.tracks)
              return this.trackListActions.setLoadedTracks(loadedTracks);
            }))
        )
      );
    };
  }

/*
search = (action$, state$) => action$.pipe(
  ofType(TrackListActions.FIND_TRACK),
  switchMap(action => this.http.get('rest/track/searchTrack/' + action$.findName)
    .pipe(map(loadedTracks => loadedTracks as Track[]))
    .pipe(map (loadedTracks => {
      return this.trackListActions.setLoadedTracks(loadedTracks);
    }))
  )
)

 */
  /*
  removeTrackEpic(): Epic<Action> {
    return action$ => {
      return action$.pipe(
        ofType(TrackListActions.REMOVE_TRACK),
        mergeMap((action:RemoveTrackAction) =>
          this.http.get(`rest/track/deleteTrack/${(action.track)}`)
            .pipe(map(loadedTracks => {
              return this.trackListActions.setLoadedTracks(loadedTracks);
            }))
        )
      );
    };
  }
  
   */


  searchTrackEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(TrackListActions.FIND_TRACK),
        mergeMap((action:FindTrackAction) =>
          this.http.get(`rest/track/searchTrack/${(action.findName)}`)
            .pipe(map(loadedTracks => loadedTracks as Track[]))
            .pipe(map(loadedTracks => {
              return this.trackListActions.setLoadedTracks(loadedTracks);
            }))
        )
      );
    };
  }






}
