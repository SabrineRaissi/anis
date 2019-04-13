import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILastThesesMySuffix } from 'app/shared/model/last-theses-my-suffix.model';

type EntityResponseType = HttpResponse<ILastThesesMySuffix>;
type EntityArrayResponseType = HttpResponse<ILastThesesMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class LastThesesMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/last-theses';

    constructor(protected http: HttpClient) {}

    create(lastTheses: ILastThesesMySuffix): Observable<EntityResponseType> {
        return this.http.post<ILastThesesMySuffix>(this.resourceUrl, lastTheses, { observe: 'response' });
    }

    update(lastTheses: ILastThesesMySuffix): Observable<EntityResponseType> {
        return this.http.put<ILastThesesMySuffix>(this.resourceUrl, lastTheses, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ILastThesesMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILastThesesMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
