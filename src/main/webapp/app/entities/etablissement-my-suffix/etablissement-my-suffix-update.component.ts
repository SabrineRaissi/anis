import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';
import { EtablissementMySuffixService } from './etablissement-my-suffix.service';

@Component({
    selector: 'jhi-etablissement-my-suffix-update',
    templateUrl: './etablissement-my-suffix-update.component.html'
})
export class EtablissementMySuffixUpdateComponent implements OnInit {
    etablissement: IEtablissementMySuffix;
    isSaving: boolean;

    constructor(protected etablissementService: EtablissementMySuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ etablissement }) => {
            this.etablissement = etablissement;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.etablissement.id !== undefined) {
            this.subscribeToSaveResponse(this.etablissementService.update(this.etablissement));
        } else {
            this.subscribeToSaveResponse(this.etablissementService.create(this.etablissement));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtablissementMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEtablissementMySuffix>) => this.onSaveSuccess(),
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
