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

  public isVisible: boolean = false;
  public message: string = "";

  ngOnInit(): void {
    this.genreListData$.subscribe((genreListData: GenreListData) => {
      this.genreListData = genreListData;
    });
    this.ngRedux.dispatch(this.genreListActions.loadGenres());
  }
  checkInput(input:string): boolean
  {
    return (!input || /^\s*$/.test(input));
  }

  showAlert(message: string) : void {
    if (this.isVisible) {
      return;
    }
    this.isVisible = true;
    this.message = message;
    setTimeout(()=> this.isVisible = false,2500);
  }


  addGenre(name: string, id: string) {
    if (this.checkInput(name) && this.checkInput(id))
    {
      this.showAlert("Вы заполнил поле");
    }
    else {
      this.ngRedux.dispatch(this.genreListActions.addGenre(new Genre(name, id)));
    }
  }

  removeGenre(genre: Genre) {
    this.ngRedux.dispatch(this.genreListActions.removeGenre(genre));
  }

  findGenre(findName: string)
  {
    if (this.checkInput(findName))
    {
      this.showAlert("Вы не заполнили поле");
    }
    else {
      this.ngRedux.dispatch(this.genreListActions.findGenre(findName));
    }
  }

  updateGenre(id:string, name:string)
  {
    this.ngRedux.dispatch(this.genreListActions.updateGenre(new Genre(name, id)));
  }

}
