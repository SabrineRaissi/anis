import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IAppStatusMySuffix } from 'app/shared/model/app-status-my-suffix.model';
import { AppStatusMySuffixService } from './app-status-my-suffix.service';

@Component({
    selector: 'jhi-app-status-my-suffix-update',
    templateUrl: './app-status-my-suffix-update.component.html'
})
export class AppStatusMySuffixUpdateComponent implements OnInit {
    appStatus: IAppStatusMySuffix;
    isSaving: boolean;

    constructor(protected appStatusService: AppStatusMySuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ appStatus }) => {
            this.appStatus = appStatus;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.appStatus.id !== undefined) {
            this.subscribeToSaveResponse(this.appStatusService.update(this.appStatus));
        } else {
            this.subscribeToSaveResponse(this.appStatusService.create(this.appStatus));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAppStatusMySuffix>>) {
        result.subscribe((res: HttpResponse<IAppStatusMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
