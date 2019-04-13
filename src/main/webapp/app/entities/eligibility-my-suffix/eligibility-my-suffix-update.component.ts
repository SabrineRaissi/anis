import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';
import { EligibilityMySuffixService } from './eligibility-my-suffix.service';
import { IEtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';
import { EtablissementMySuffixService } from 'app/entities/etablissement-my-suffix';
import { ITheseMySuffix } from 'app/shared/model/these-my-suffix.model';
import { TheseMySuffixService } from 'app/entities/these-my-suffix';

@Component({
    selector: 'jhi-eligibility-my-suffix-update',
    templateUrl: './eligibility-my-suffix-update.component.html'
})
export class EligibilityMySuffixUpdateComponent implements OnInit {
    eligibility: IEligibilityMySuffix;
    isSaving: boolean;

    etablissements: IEtablissementMySuffix[];

    these: ITheseMySuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected eligibilityService: EligibilityMySuffixService,
        protected etablissementService: EtablissementMySuffixService,
        protected theseService: TheseMySuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ eligibility }) => {
            this.eligibility = eligibility;
        });
        this.etablissementService
            .query({ filter: 'eligibility-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IEtablissementMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEtablissementMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IEtablissementMySuffix[]) => {
                    if (!this.eligibility.etablissementId) {
                        this.etablissements = res;
                    } else {
                        this.etablissementService
                            .find(this.eligibility.etablissementId)
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
        this.theseService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITheseMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITheseMySuffix[]>) => response.body)
            )
            .subscribe((res: ITheseMySuffix[]) => (this.these = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.eligibility.id !== undefined) {
            this.subscribeToSaveResponse(this.eligibilityService.update(this.eligibility));
        } else {
            this.subscribeToSaveResponse(this.eligibilityService.create(this.eligibility));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEligibilityMySuffix>>) {
        result.subscribe((res: HttpResponse<IEligibilityMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTheseById(index: number, item: ITheseMySuffix) {
        return item.id;
    }
}
