import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStatusEtapeInscriptionMySuffix } from 'app/shared/model/status-etape-inscription-my-suffix.model';

type EntityResponseType = HttpResponse<IStatusEtapeInscriptionMySuffix>;
type EntityArrayResponseType = HttpResponse<IStatusEtapeInscriptionMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class StatusEtapeInscriptionMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/status-etape-inscriptions';

    constructor(protected http: HttpClient) {}

    create(statusEtapeInscription: IStatusEtapeInscriptionMySuffix): Observable<EntityResponseType> {
        return this.http.post<IStatusEtapeInscriptionMySuffix>(this.resourceUrl, statusEtapeInscription, { observe: 'response' });
    }

    update(statusEtapeInscription: IStatusEtapeInscriptionMySuffix): Observable<EntityResponseType> {
        return this.http.put<IStatusEtapeInscriptionMySuffix>(this.resourceUrl, statusEtapeInscription, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IStatusEtapeInscriptionMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStatusEtapeInscriptionMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
