import {GenreListData, GenreListState} from './genre-list.data';
import {AddGenreAction, GenreListAction, GenreListActions, RemoveGenreAction, SetLoadedGenresAction} from './genre-list.actions';

const INITIAL_STATE: GenreListState = {genreListData: new GenreListData([])};

export function genreListReducer(state: GenreListState = INITIAL_STATE, action: GenreListAction): GenreListState {
  switch (action.type) {
    case GenreListActions.ADD_GENRE:
      state.genreListData.genres.push((action as AddGenreAction).genre);
      return {genreListData: new GenreListData(state.genreListData.genres)};
    case GenreListActions.REMOVE_GENRE:
      return {
        genreListData: new GenreListData(
          state.genreListData.genres.filter(genre => genre !== (action as RemoveGenreAction).genre)
        )
      };
    case GenreListActions.SET_LOADED_GENRES:
      return {
        genreListData: new GenreListData((action as SetLoadedGenresAction).genres)
      };
  }
  return state;
}
