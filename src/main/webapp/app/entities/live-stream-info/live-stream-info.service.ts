import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILiveStreamInfo } from 'app/shared/model/live-stream-info.model';

type EntityResponseType = HttpResponse<ILiveStreamInfo>;
type EntityArrayResponseType = HttpResponse<ILiveStreamInfo[]>;

@Injectable({ providedIn: 'root' })
export class LiveStreamInfoService {
    private resourceUrl = SERVER_API_URL + 'api/live-stream-infos';

    constructor(private http: HttpClient) {}

    create(liveStreamInfo: ILiveStreamInfo): Observable<EntityResponseType> {
        return this.http.post<ILiveStreamInfo>(this.resourceUrl, liveStreamInfo, { observe: 'response' });
    }

    update(liveStreamInfo: ILiveStreamInfo): Observable<EntityResponseType> {
        return this.http.put<ILiveStreamInfo>(this.resourceUrl, liveStreamInfo, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<ILiveStreamInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILiveStreamInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
