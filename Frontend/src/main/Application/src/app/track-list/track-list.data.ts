export interface TrackListState {
  trackListData: TrackListData;
}

export class TrackListData {
  constructor(public tracks: Track[]) {}
}

export class Track {
  constructor(public name: string,
              public author: string) {
  }
}
