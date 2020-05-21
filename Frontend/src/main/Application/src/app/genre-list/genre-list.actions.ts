import {Injectable} from '@angular/core';
import {Genre} from './genre-list.data';
import {Action} from 'redux';

@Injectable()
export class GenreListActions {
  static readonly ADD_GENRE: string = 'ADD_GENRE';
  static readonly REMOVE_GENRE: string = 'REMOVE_GENRE';
  static readonly LOAD_GENRES: string = 'LOAD_GENRES';
  static readonly SET_LOADED_GENRES: string = 'SET_LOADED_GENRES';

  addGenre(genre: Genre): AddGenreAction {
    return {genre, type: GenreListActions.ADD_GENRE};
  }

  removeGenre(genre: Genre): RemoveGenreAction {
    return {genre, type: GenreListActions.REMOVE_GENRE};
  }

  loadGenres(): LoadGenresAction {
    return {type: GenreListActions.LOAD_GENRES};
  }

  setLoadedGenres(genres: Genre[]): SetLoadedGenresAction {
    return {genres, type: GenreListActions.SET_LOADED_GENRES};
  }

}

export interface GenreListAction extends Action {
}

export interface AddGenreAction extends GenreListAction {
  genre: Genre;
}

export interface RemoveGenreAction extends GenreListAction {
  genre: Genre;
}

export interface LoadGenresAction extends GenreListAction {
}

export interface SetLoadedGenresAction extends GenreListAction {
  genres: Genre[];
}
