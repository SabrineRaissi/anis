import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISessionInscriptionMySuffix } from 'app/shared/model/session-inscription-my-suffix.model';

type EntityResponseType = HttpResponse<ISessionInscriptionMySuffix>;
type EntityArrayResponseType = HttpResponse<ISessionInscriptionMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class SessionInscriptionMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/session-inscriptions';

    constructor(protected http: HttpClient) {}

    create(sessionInscription: ISessionInscriptionMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sessionInscription);
        return this.http
            .post<ISessionInscriptionMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sessionInscription: ISessionInscriptionMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sessionInscription);
        return this.http
            .put<ISessionInscriptionMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISessionInscriptionMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISessionInscriptionMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sessionInscription: ISessionInscriptionMySuffix): ISessionInscriptionMySuffix {
        const copy: ISessionInscriptionMySuffix = Object.assign({}, sessionInscription, {
            startDate:
                sessionInscription.startDate != null && sessionInscription.startDate.isValid()
                    ? sessionInscription.startDate.toJSON()
                    : null,
            endDate: sessionInscription.endDate != null && sessionInscription.endDate.isValid() ? sessionInscription.endDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
            res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sessionInscription: ISessionInscriptionMySuffix) => {
                sessionInscription.startDate = sessionInscription.startDate != null ? moment(sessionInscription.startDate) : null;
                sessionInscription.endDate = sessionInscription.endDate != null ? moment(sessionInscription.endDate) : null;
            });
        }
        return res;
    }
}
