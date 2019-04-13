import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDeviceMetadataMySuffix } from 'app/shared/model/device-metadata-my-suffix.model';

type EntityResponseType = HttpResponse<IDeviceMetadataMySuffix>;
type EntityArrayResponseType = HttpResponse<IDeviceMetadataMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class DeviceMetadataMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/device-metadata';

    constructor(protected http: HttpClient) {}

    create(deviceMetadata: IDeviceMetadataMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(deviceMetadata);
        return this.http
            .post<IDeviceMetadataMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(deviceMetadata: IDeviceMetadataMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(deviceMetadata);
        return this.http
            .put<IDeviceMetadataMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDeviceMetadataMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDeviceMetadataMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(deviceMetadata: IDeviceMetadataMySuffix): IDeviceMetadataMySuffix {
        const copy: IDeviceMetadataMySuffix = Object.assign({}, deviceMetadata, {
            lastLoggedIn:
                deviceMetadata.lastLoggedIn != null && deviceMetadata.lastLoggedIn.isValid() ? deviceMetadata.lastLoggedIn.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.lastLoggedIn = res.body.lastLoggedIn != null ? moment(res.body.lastLoggedIn) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((deviceMetadata: IDeviceMetadataMySuffix) => {
                deviceMetadata.lastLoggedIn = deviceMetadata.lastLoggedIn != null ? moment(deviceMetadata.lastLoggedIn) : null;
            });
        }
        return res;
    }
}
