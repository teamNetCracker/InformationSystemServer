import { Component, OnInit } from '@angular/core';
import {NgRedux, select} from '@angular-redux/store';
import {AppState} from '../state/app.types';
import {GenreListActions} from './genre-list.actions';
import {Observable} from 'rxjs';
import {Genre, GenreListData} from './genre-list.data';

@Component({
  selector: 'app-genre-list',
  templateUrl: './genre-list.component.html',
  styleUrls: ['./genre-list.component.css']
})
export class GenreListComponent implements OnInit {
  @select(['genreListState', 'genreListData']) readonly genreListData$: Observable<GenreListData>;
  genreListData: GenreListData;

  constructor(private ngRedux: NgRedux<AppState>, private genreListActions: GenreListActions) { }

  ngOnInit(): void {
    this.genreListData$.subscribe((genreListData: GenreListData) => {
      this.genreListData = genreListData;
    });
    this.ngRedux.dispatch(this.genreListActions.loadGenres());
  }

  addGenre(name: string) {
    this.ngRedux.dispatch(this.genreListActions.addGenre(new Genre(name)));
  }

  removeGenre(genre: Genre) {
    this.ngRedux.dispatch(this.genreListActions.removeGenre(genre));
  }
}
