import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPossibleValueMySuffix } from 'app/shared/model/possible-value-my-suffix.model';

type EntityResponseType = HttpResponse<IPossibleValueMySuffix>;
type EntityArrayResponseType = HttpResponse<IPossibleValueMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class PossibleValueMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/possible-values';

    constructor(protected http: HttpClient) {}

    create(possibleValue: IPossibleValueMySuffix): Observable<EntityResponseType> {
        return this.http.post<IPossibleValueMySuffix>(this.resourceUrl, possibleValue, { observe: 'response' });
    }

    update(possibleValue: IPossibleValueMySuffix): Observable<EntityResponseType> {
        return this.http.put<IPossibleValueMySuffix>(this.resourceUrl, possibleValue, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPossibleValueMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPossibleValueMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
