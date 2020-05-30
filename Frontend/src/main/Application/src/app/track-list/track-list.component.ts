import {Component, OnInit} from '@angular/core';
import {Track, TrackListData} from './track-list.data';
import {Observable} from 'rxjs';
import { NgRedux, select } from '@angular-redux/store';
import {AppState} from '../state/app.types';
import {TrackListActions} from './track-list.action';
import {Genre} from "../genre-list/genre-list.data";

@Component({
  selector: 'app-track-list',
  templateUrl: './track-list.component.html',
  styleUrls: ['./track-list.component.css']
})
export class TrackListComponent implements OnInit {
  @select(['trackListState', 'trackListData']) readonly trackListData$: Observable<TrackListData>;
  trackListData: TrackListData;

  constructor(private ngRedux: NgRedux<AppState>, private trackListActions: TrackListActions) { }

  ngOnInit(): void {
    this.trackListData$.subscribe((trackListData: TrackListData) => {
      this.trackListData = trackListData;
    });
    this.ngRedux.dispatch(this.trackListActions.loadTracks());
  }

  addTrack(name: string, author: string, id:string, album:string, genre:Genre, duration:number) {
    this.ngRedux.dispatch(this.trackListActions.addTrack(new Track(id,name, author, album, genre, duration)));
  }

  removeTrack(track: Track) {
    this.ngRedux.dispatch(this.trackListActions.removeTrack(track));
  }

}
