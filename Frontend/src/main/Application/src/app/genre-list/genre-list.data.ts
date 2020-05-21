export interface GenreListState {
  genreListData: GenreListData;
}

export class GenreListData {
  constructor(public genres: Genre[]) {}
}

export class Genre {
  constructor(public name: string) {
  }
}
