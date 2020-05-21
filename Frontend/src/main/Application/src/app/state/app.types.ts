import { TrackListState } from '../track-list/track-list.data';
import {GenreListState} from '../genre-list/genre-list.data';

export interface AppState {
  trackListState?: TrackListState;
  genreListState?: GenreListState;
}
