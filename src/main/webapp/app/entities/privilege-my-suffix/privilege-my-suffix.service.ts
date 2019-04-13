import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPrivilegeMySuffix } from 'app/shared/model/privilege-my-suffix.model';

type EntityResponseType = HttpResponse<IPrivilegeMySuffix>;
type EntityArrayResponseType = HttpResponse<IPrivilegeMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class PrivilegeMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/privileges';

    constructor(protected http: HttpClient) {}

    create(privilege: IPrivilegeMySuffix): Observable<EntityResponseType> {
        return this.http.post<IPrivilegeMySuffix>(this.resourceUrl, privilege, { observe: 'response' });
    }

    update(privilege: IPrivilegeMySuffix): Observable<EntityResponseType> {
        return this.http.put<IPrivilegeMySuffix>(this.resourceUrl, privilege, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPrivilegeMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPrivilegeMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
