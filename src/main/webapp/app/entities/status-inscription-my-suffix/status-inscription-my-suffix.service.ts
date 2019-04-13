import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStatusInscriptionMySuffix } from 'app/shared/model/status-inscription-my-suffix.model';

type EntityResponseType = HttpResponse<IStatusInscriptionMySuffix>;
type EntityArrayResponseType = HttpResponse<IStatusInscriptionMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class StatusInscriptionMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/status-inscriptions';

    constructor(protected http: HttpClient) {}

    create(statusInscription: IStatusInscriptionMySuffix): Observable<EntityResponseType> {
        return this.http.post<IStatusInscriptionMySuffix>(this.resourceUrl, statusInscription, { observe: 'response' });
    }

    update(statusInscription: IStatusInscriptionMySuffix): Observable<EntityResponseType> {
        return this.http.put<IStatusInscriptionMySuffix>(this.resourceUrl, statusInscription, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IStatusInscriptionMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStatusInscriptionMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
