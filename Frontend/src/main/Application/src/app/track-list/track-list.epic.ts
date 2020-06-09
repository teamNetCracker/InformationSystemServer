import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {
  AddTrackAction,
  FindTrackAction,
  RemoveTrackAction,
  TrackListActions,
  UpdateTrackAction
} from './track-list.action';
import {ActionsObservable, Epic, ofType} from 'redux-observable';
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
              return this.trackListActions.setLoadedTracks(loadedTracks);
            }))
        )
      );
    };
  }

  addTrackEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(TrackListActions.ADD_TRACK),
        mergeMap((action:AddTrackAction) =>
          this.http.post('rest/track/addTrack', action.track)
            .pipe(map((response) => {
              console.log(response)
              return this.trackListActions.loadTracks();
            }))
        )
      );
    };
  }


  removeTrackEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(TrackListActions.REMOVE_TRACK),
        mergeMap((action:RemoveTrackAction) =>
          this.http.delete(`rest/track/deleteTrack/${(action.track.id)}`)
            .pipe(map((response:boolean) => {
              return this.trackListActions.loadTracks();
            }))
        )
      );
    };
  }

  updateTrackEpic(): Epic<Action, Action> {
    return action$ => {
      return action$.pipe(
        ofType(TrackListActions.UPDATE_TRACK),
        mergeMap((action:UpdateTrackAction) =>
          this.http.put(`rest/track/updateTrack/`, action.track)
            .pipe(map((response:boolean) => {
              return this.trackListActions.loadTracks();
            }))
        )
      );
    };
  }


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
