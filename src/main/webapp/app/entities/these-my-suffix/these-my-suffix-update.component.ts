import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITheseMySuffix } from 'app/shared/model/these-my-suffix.model';
import { TheseMySuffixService } from './these-my-suffix.service';
import { IEtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';
import { EtablissementMySuffixService } from 'app/entities/etablissement-my-suffix';

@Component({
    selector: 'jhi-these-my-suffix-update',
    templateUrl: './these-my-suffix-update.component.html'
})
export class TheseMySuffixUpdateComponent implements OnInit {
    these: ITheseMySuffix;
    isSaving: boolean;

    etablissements: IEtablissementMySuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected theseService: TheseMySuffixService,
        protected etablissementService: EtablissementMySuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ these }) => {
            this.these = these;
        });
        this.etablissementService
            .query({ filter: 'these-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IEtablissementMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEtablissementMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IEtablissementMySuffix[]) => {
                    if (!this.these.etablissementId) {
                        this.etablissements = res;
                    } else {
                        this.etablissementService
                            .find(this.these.etablissementId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IEtablissementMySuffix>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IEtablissementMySuffix>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IEtablissementMySuffix) => (this.etablissements = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.these.id !== undefined) {
            this.subscribeToSaveResponse(this.theseService.update(this.these));
        } else {
            this.subscribeToSaveResponse(this.theseService.create(this.these));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITheseMySuffix>>) {
        result.subscribe((res: HttpResponse<ITheseMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEtablissementById(index: number, item: IEtablissementMySuffix) {
        return item.id;
    }
}
