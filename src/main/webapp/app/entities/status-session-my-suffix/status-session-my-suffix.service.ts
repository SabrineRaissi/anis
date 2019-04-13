import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStatusSessionMySuffix } from 'app/shared/model/status-session-my-suffix.model';

type EntityResponseType = HttpResponse<IStatusSessionMySuffix>;
type EntityArrayResponseType = HttpResponse<IStatusSessionMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class StatusSessionMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/status-sessions';

    constructor(protected http: HttpClient) {}

    create(statusSession: IStatusSessionMySuffix): Observable<EntityResponseType> {
        return this.http.post<IStatusSessionMySuffix>(this.resourceUrl, statusSession, { observe: 'response' });
    }

    update(statusSession: IStatusSessionMySuffix): Observable<EntityResponseType> {
        return this.http.put<IStatusSessionMySuffix>(this.resourceUrl, statusSession, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IStatusSessionMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStatusSessionMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
