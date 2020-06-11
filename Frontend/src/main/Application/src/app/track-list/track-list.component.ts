import {Component, OnInit} from '@angular/core';
import {Track, TrackListData} from './track-list.data';
import {Observable} from 'rxjs';
import { NgRedux, select } from '@angular-redux/store';
import {AppState} from '../state/app.types';
import {TrackListActions} from './track-list.action';
import {Genre, GenreListData} from "../genre-list/genre-list.data";
import {isEmpty} from "rxjs/operators";



@Component({
  selector: 'app-track-list',
  templateUrl: './track-list.component.html',
  styleUrls: ['./track-list.component.css']
})
export class TrackListComponent implements OnInit {
  @select(['trackListState', 'trackListData']) readonly trackListData$: Observable<TrackListData>;
  @select(['genreListState', 'genreListData']) readonly genreListData$: Observable<GenreListData>;
  trackListData: TrackListData; genreListData: GenreListData;

  constructor(private ngRedux: NgRedux<AppState>, private trackListActions: TrackListActions) { }

  public isVisible: boolean = false;
  public message: string = "";

  ngOnInit(): void {
    this.trackListData$.subscribe((trackListData: TrackListData) => {
      this.trackListData = trackListData;
    });
    this.genreListData$.subscribe((genreListData: GenreListData) =>
    {
      this.genreListData = genreListData;
    })
    this.ngRedux.dispatch(this.trackListActions.loadTracks());
  }

  checkInput(input:string): boolean
  {
    return (!input || /^\s*$/.test(input));
  }

  showAlert(message:string) : void {
    if (this.isVisible) {
      return;
    }
    this.message = message;
    this.isVisible = true;
    setTimeout(()=> this.isVisible = false,2500);
  }

  updateTableTrack(): void
  {
    this.ngRedux.dispatch(this.trackListActions.loadTracks());
  }


  updateTrack(id: string, name:string,author:string, album:string, duration:string, idGenre:string, nameGenre:string)
  {
    if (this.checkInput(name) || this.checkInput(author) || this.checkInput(album) || this.checkInput(duration))
    {
      this.showAlert("Вы заполнили не все поля");
    }
    else {
      let track = new Track(id, name, author, album, new Genre(nameGenre, idGenre), parseInt(duration));
      this.ngRedux.dispatch(this.trackListActions.updateTrack(track));
    }
  }

  addTrack(name: string, author: string, id:string, album:string, duration:string, idGenre:string, nameGenre: string) {
    if (this.checkInput(name) || this.checkInput(author) || this.checkInput(album) || this.checkInput(duration) )
    {
      this.showAlert("Вы заполнили не все поля");
    }
    else {
      let track = new Track(id, name, author, album, new Genre(nameGenre, idGenre), parseInt(duration));
      this.ngRedux.dispatch(this.trackListActions.addTrack(track));
    }
  }

  removeTrack(track: Track) {
    this.ngRedux.dispatch(this.trackListActions.removeTrack(track));
  }

  findTrack(findName: string)
  {
    if (this.checkInput(findName))
    {
      this.showAlert("Вы не заполнили поле");
    }
    {
      this.ngRedux.dispatch(this.trackListActions.findTrack(findName));
    }
  }


}
