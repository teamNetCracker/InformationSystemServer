import {Injectable} from '@angular/core';
import {Genre} from './genre-list.data';
import {Action} from 'redux';

@Injectable()
export class GenreListActions {
  static readonly ADD_GENRE: string = 'ADD_GENRE';
  static readonly REMOVE_GENRE: string = 'REMOVE_GENRE';
  static readonly LOAD_GENRES: string = 'LOAD_GENRES';
  static readonly SET_LOADED_GENRES: string = 'SET_LOADED_GENRES';
  static readonly FIND_GENRE: string = 'FIND_GENRE';
  static readonly UPDATE_GENRE: string = 'UPDATE_GENRE';
  static readonly SET_FIND_GENRE: string = 'SET_FIND_GENRE'

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

  findGenre(findName: string): FindGenreAction
  {
    return {findName, type: GenreListActions.FIND_GENRE}
  }

  updateGenre(genre: Genre): UpdateGenreAction
  {
    return {genre, type: GenreListActions.UPDATE_GENRE}
  }
  setFindGenre(genre: Genre): SetFindGenreAction
  {
    return {genre, type: GenreListActions.SET_FIND_GENRE}
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

export interface FindGenreAction extends GenreListAction{
  findName: string;
}

export interface UpdateGenreAction extends GenreListAction{
  genre: Genre;
}
export interface SetFindGenreAction  extends GenreListAction
{
  genre: Genre;

}
