import {Injectable} from '@angular/core';
import {Track} from './track-list.data';
import {Action} from 'redux';

@Injectable()
export class TrackListActions {
  static readonly ADD_TRACK: string = 'ADD_TRACK';
  static readonly REMOVE_TRACK: string = 'REMOVE_TRACK';
  static readonly LOAD_TRACKS: string = 'LOAD_TRACKS';
  static readonly SET_LOADED_TRACKS: string = 'SET_LOADED_TRACKS';

  addTrack(track: Track): AddTrackAction {
    return {track, type: TrackListActions.ADD_TRACK};
  }

  removeTrack(track: Track): RemoveTrackAction {
    return {track, type: TrackListActions.REMOVE_TRACK};
  }

  loadTracks(): LoadTracksAction {
    return {type: TrackListActions.LOAD_TRACKS};
  }

  setLoadedTracks(tracks: Track[]): SetLoadedTracksAction {
    return {tracks, type: TrackListActions.SET_LOADED_TRACKS};
  }
}

export interface TrackListAction extends Action {
}

export interface AddTrackAction extends TrackListAction {
  track: Track;
}

export interface RemoveTrackAction extends TrackListAction {
  track: Track;
}

export interface LoadTracksAction extends TrackListAction {
}

export interface SetLoadedTracksAction extends TrackListAction {
  tracks: Track[];
}
