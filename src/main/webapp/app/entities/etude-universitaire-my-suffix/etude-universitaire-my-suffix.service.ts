import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEtudeUniversitaireMySuffix } from 'app/shared/model/etude-universitaire-my-suffix.model';

type EntityResponseType = HttpResponse<IEtudeUniversitaireMySuffix>;
type EntityArrayResponseType = HttpResponse<IEtudeUniversitaireMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class EtudeUniversitaireMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/etude-universitaires';

    constructor(protected http: HttpClient) {}

    create(etudeUniversitaire: IEtudeUniversitaireMySuffix): Observable<EntityResponseType> {
        return this.http.post<IEtudeUniversitaireMySuffix>(this.resourceUrl, etudeUniversitaire, { observe: 'response' });
    }

    update(etudeUniversitaire: IEtudeUniversitaireMySuffix): Observable<EntityResponseType> {
        return this.http.put<IEtudeUniversitaireMySuffix>(this.resourceUrl, etudeUniversitaire, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEtudeUniversitaireMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEtudeUniversitaireMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
