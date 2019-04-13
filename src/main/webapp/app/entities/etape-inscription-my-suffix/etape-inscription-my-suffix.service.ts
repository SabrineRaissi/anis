import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEtapeInscriptionMySuffix } from 'app/shared/model/etape-inscription-my-suffix.model';

type EntityResponseType = HttpResponse<IEtapeInscriptionMySuffix>;
type EntityArrayResponseType = HttpResponse<IEtapeInscriptionMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class EtapeInscriptionMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/etape-inscriptions';

    constructor(protected http: HttpClient) {}

    create(etapeInscription: IEtapeInscriptionMySuffix): Observable<EntityResponseType> {
        return this.http.post<IEtapeInscriptionMySuffix>(this.resourceUrl, etapeInscription, { observe: 'response' });
    }

    update(etapeInscription: IEtapeInscriptionMySuffix): Observable<EntityResponseType> {
        return this.http.put<IEtapeInscriptionMySuffix>(this.resourceUrl, etapeInscription, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEtapeInscriptionMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEtapeInscriptionMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
