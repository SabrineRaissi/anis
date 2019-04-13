import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';

type EntityResponseType = HttpResponse<IEligibilityMySuffix>;
type EntityArrayResponseType = HttpResponse<IEligibilityMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class EligibilityMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/eligibilities';

    constructor(protected http: HttpClient) {}

    create(eligibility: IEligibilityMySuffix): Observable<EntityResponseType> {
        return this.http.post<IEligibilityMySuffix>(this.resourceUrl, eligibility, { observe: 'response' });
    }

    update(eligibility: IEligibilityMySuffix): Observable<EntityResponseType> {
        return this.http.put<IEligibilityMySuffix>(this.resourceUrl, eligibility, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEligibilityMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEligibilityMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
