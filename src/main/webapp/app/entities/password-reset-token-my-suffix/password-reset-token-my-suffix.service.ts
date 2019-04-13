import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPasswordResetTokenMySuffix } from 'app/shared/model/password-reset-token-my-suffix.model';

type EntityResponseType = HttpResponse<IPasswordResetTokenMySuffix>;
type EntityArrayResponseType = HttpResponse<IPasswordResetTokenMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class PasswordResetTokenMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/password-reset-tokens';

    constructor(protected http: HttpClient) {}

    create(passwordResetToken: IPasswordResetTokenMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(passwordResetToken);
        return this.http
            .post<IPasswordResetTokenMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(passwordResetToken: IPasswordResetTokenMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(passwordResetToken);
        return this.http
            .put<IPasswordResetTokenMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPasswordResetTokenMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPasswordResetTokenMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(passwordResetToken: IPasswordResetTokenMySuffix): IPasswordResetTokenMySuffix {
        const copy: IPasswordResetTokenMySuffix = Object.assign({}, passwordResetToken, {
            expiryDate:
                passwordResetToken.expiryDate != null && passwordResetToken.expiryDate.isValid()
                    ? passwordResetToken.expiryDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.expiryDate = res.body.expiryDate != null ? moment(res.body.expiryDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((passwordResetToken: IPasswordResetTokenMySuffix) => {
                passwordResetToken.expiryDate = passwordResetToken.expiryDate != null ? moment(passwordResetToken.expiryDate) : null;
            });
        }
        return res;
    }
}
