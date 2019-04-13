import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IStatusSessionMySuffix } from 'app/shared/model/status-session-my-suffix.model';
import { StatusSessionMySuffixService } from './status-session-my-suffix.service';

@Component({
    selector: 'jhi-status-session-my-suffix-update',
    templateUrl: './status-session-my-suffix-update.component.html'
})
export class StatusSessionMySuffixUpdateComponent implements OnInit {
    statusSession: IStatusSessionMySuffix;
    isSaving: boolean;

    constructor(protected statusSessionService: StatusSessionMySuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ statusSession }) => {
            this.statusSession = statusSession;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.statusSession.id !== undefined) {
            this.subscribeToSaveResponse(this.statusSessionService.update(this.statusSession));
        } else {
            this.subscribeToSaveResponse(this.statusSessionService.create(this.statusSession));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStatusSessionMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IStatusSessionMySuffix>) => this.onSaveSuccess(),
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
