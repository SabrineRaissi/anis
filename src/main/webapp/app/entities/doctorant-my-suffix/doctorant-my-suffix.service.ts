import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';

type EntityResponseType = HttpResponse<IDoctorantMySuffix>;
type EntityArrayResponseType = HttpResponse<IDoctorantMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class DoctorantMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/doctorants';

    constructor(protected http: HttpClient) {}

    create(doctorant: IDoctorantMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(doctorant);
        return this.http
            .post<IDoctorantMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(doctorant: IDoctorantMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(doctorant);
        return this.http
            .put<IDoctorantMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDoctorantMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDoctorantMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(doctorant: IDoctorantMySuffix): IDoctorantMySuffix {
        const copy: IDoctorantMySuffix = Object.assign({}, doctorant, {
            dateNissance: doctorant.dateNissance != null && doctorant.dateNissance.isValid() ? doctorant.dateNissance.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateNissance = res.body.dateNissance != null ? moment(res.body.dateNissance) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((doctorant: IDoctorantMySuffix) => {
                doctorant.dateNissance = doctorant.dateNissance != null ? moment(doctorant.dateNissance) : null;
            });
        }
        return res;
    }
}
