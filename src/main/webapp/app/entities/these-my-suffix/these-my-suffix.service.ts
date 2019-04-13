import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITheseMySuffix } from 'app/shared/model/these-my-suffix.model';

type EntityResponseType = HttpResponse<ITheseMySuffix>;
type EntityArrayResponseType = HttpResponse<ITheseMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class TheseMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/these';

    constructor(protected http: HttpClient) {}

    create(these: ITheseMySuffix): Observable<EntityResponseType> {
        return this.http.post<ITheseMySuffix>(this.resourceUrl, these, { observe: 'response' });
    }

    update(these: ITheseMySuffix): Observable<EntityResponseType> {
        return this.http.put<ITheseMySuffix>(this.resourceUrl, these, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITheseMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITheseMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
