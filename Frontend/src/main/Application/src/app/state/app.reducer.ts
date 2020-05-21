import {combineReducers} from 'redux';
import {trackListReducer} from '../track-list/track-list.reducer';
import {genreListReducer} from '../genre-list/genre-list.reducer';


export const appReducer = combineReducers({
  trackListState: trackListReducer,
  genreListState: genreListReducer
});
