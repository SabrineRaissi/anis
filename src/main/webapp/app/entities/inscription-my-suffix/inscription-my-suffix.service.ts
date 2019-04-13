import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInscriptionMySuffix } from 'app/shared/model/inscription-my-suffix.model';

type EntityResponseType = HttpResponse<IInscriptionMySuffix>;
type EntityArrayResponseType = HttpResponse<IInscriptionMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class InscriptionMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/inscriptions';

    constructor(protected http: HttpClient) {}

    create(inscription: IInscriptionMySuffix): Observable<EntityResponseType> {
        return this.http.post<IInscriptionMySuffix>(this.resourceUrl, inscription, { observe: 'response' });
    }

    update(inscription: IInscriptionMySuffix): Observable<EntityResponseType> {
        return this.http.put<IInscriptionMySuffix>(this.resourceUrl, inscription, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IInscriptionMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IInscriptionMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
