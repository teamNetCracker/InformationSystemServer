import {TrackListState, TrackListData} from './track-list.data';
import {AddTrackAction, RemoveTrackAction, TrackListAction, SetLoadedTracksAction, TrackListActions} from './track-list.action';

const INITIAL_STATE: TrackListState = {trackListData: new TrackListData([])};

export function trackListReducer(state: TrackListState = INITIAL_STATE, action: TrackListAction): TrackListState {
  switch (action.type) {
    case TrackListActions.ADD_TRACK:
      state.trackListData.tracks.push((action as AddTrackAction).track);
      return {trackListData: new TrackListData(state.trackListData.tracks)};
    case TrackListActions.REMOVE_TRACK:
      return {
        trackListData: new TrackListData(
          state.trackListData.tracks.filter(track => track !== (action as RemoveTrackAction).track)
        )
      };
    case TrackListActions.SET_LOADED_TRACKS:
      return {
        trackListData: new TrackListData((action as SetLoadedTracksAction).tracks)
      };
  }
  return state;
}
