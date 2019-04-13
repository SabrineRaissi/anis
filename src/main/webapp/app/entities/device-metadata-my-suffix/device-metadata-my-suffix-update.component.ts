import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IDeviceMetadataMySuffix } from 'app/shared/model/device-metadata-my-suffix.model';
import { DeviceMetadataMySuffixService } from './device-metadata-my-suffix.service';

@Component({
    selector: 'jhi-device-metadata-my-suffix-update',
    templateUrl: './device-metadata-my-suffix-update.component.html'
})
export class DeviceMetadataMySuffixUpdateComponent implements OnInit {
    deviceMetadata: IDeviceMetadataMySuffix;
    isSaving: boolean;
    lastLoggedIn: string;

    constructor(protected deviceMetadataService: DeviceMetadataMySuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ deviceMetadata }) => {
            this.deviceMetadata = deviceMetadata;
            this.lastLoggedIn = this.deviceMetadata.lastLoggedIn != null ? this.deviceMetadata.lastLoggedIn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.deviceMetadata.lastLoggedIn = this.lastLoggedIn != null ? moment(this.lastLoggedIn, DATE_TIME_FORMAT) : null;
        if (this.deviceMetadata.id !== undefined) {
            this.subscribeToSaveResponse(this.deviceMetadataService.update(this.deviceMetadata));
        } else {
            this.subscribeToSaveResponse(this.deviceMetadataService.create(this.deviceMetadata));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeviceMetadataMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IDeviceMetadataMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
