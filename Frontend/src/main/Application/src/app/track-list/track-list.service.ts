import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {Track} from "./track-list.data";


@Injectable()
export class trackListService {
  constructor(private http: HttpClient) {}
  deleteTrack(id: string): Observable<any>
  {
    return this.http.delete('rest/track/deleteTrack/'+id).pipe();
  }
  addTrack(track: Track): Observable<Track>
  {
    return this.http.post<Track>('rest/track/addTrack/', track).pipe();
  }

}


