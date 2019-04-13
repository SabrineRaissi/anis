import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IStatusEtapeInscriptionMySuffix } from 'app/shared/model/status-etape-inscription-my-suffix.model';
import { StatusEtapeInscriptionMySuffixService } from './status-etape-inscription-my-suffix.service';

@Component({
    selector: 'jhi-status-etape-inscription-my-suffix-update',
    templateUrl: './status-etape-inscription-my-suffix-update.component.html'
})
export class StatusEtapeInscriptionMySuffixUpdateComponent implements OnInit {
    statusEtapeInscription: IStatusEtapeInscriptionMySuffix;
    isSaving: boolean;

    constructor(protected statusEtapeInscriptionService: StatusEtapeInscriptionMySuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ statusEtapeInscription }) => {
            this.statusEtapeInscription = statusEtapeInscription;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.statusEtapeInscription.id !== undefined) {
            this.subscribeToSaveResponse(this.statusEtapeInscriptionService.update(this.statusEtapeInscription));
        } else {
            this.subscribeToSaveResponse(this.statusEtapeInscriptionService.create(this.statusEtapeInscription));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStatusEtapeInscriptionMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IStatusEtapeInscriptionMySuffix>) => this.onSaveSuccess(),
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
