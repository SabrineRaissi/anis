import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAppStatusMySuffix } from 'app/shared/model/app-status-my-suffix.model';

type EntityResponseType = HttpResponse<IAppStatusMySuffix>;
type EntityArrayResponseType = HttpResponse<IAppStatusMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class AppStatusMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/app-statuses';

    constructor(protected http: HttpClient) {}

    create(appStatus: IAppStatusMySuffix): Observable<EntityResponseType> {
        return this.http.post<IAppStatusMySuffix>(this.resourceUrl, appStatus, { observe: 'response' });
    }

    update(appStatus: IAppStatusMySuffix): Observable<EntityResponseType> {
        return this.http.put<IAppStatusMySuffix>(this.resourceUrl, appStatus, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAppStatusMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAppStatusMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
