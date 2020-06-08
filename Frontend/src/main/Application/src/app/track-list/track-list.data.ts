import {Genre} from "../genre-list/genre-list.data";

export interface TrackListState {
  trackListData: TrackListData;
}

export class TrackListData {
  constructor(public tracks: Track[]) {}
}

export class Track {
  constructor(public id: string,
              public title: string,
              public performer: string,
              public album: string,
              public genre: Genre,
              public duration: number) {
  }
}
