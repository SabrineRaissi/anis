import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IStatusInscriptionMySuffix } from 'app/shared/model/status-inscription-my-suffix.model';
import { StatusInscriptionMySuffixService } from './status-inscription-my-suffix.service';

@Component({
    selector: 'jhi-status-inscription-my-suffix-update',
    templateUrl: './status-inscription-my-suffix-update.component.html'
})
export class StatusInscriptionMySuffixUpdateComponent implements OnInit {
    statusInscription: IStatusInscriptionMySuffix;
    isSaving: boolean;

    constructor(protected statusInscriptionService: StatusInscriptionMySuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ statusInscription }) => {
            this.statusInscription = statusInscription;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.statusInscription.id !== undefined) {
            this.subscribeToSaveResponse(this.statusInscriptionService.update(this.statusInscription));
        } else {
            this.subscribeToSaveResponse(this.statusInscriptionService.create(this.statusInscription));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStatusInscriptionMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IStatusInscriptionMySuffix>) => this.onSaveSuccess(),
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
