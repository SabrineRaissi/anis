import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPossibleValueMySuffix } from 'app/shared/model/possible-value-my-suffix.model';
import { PossibleValueMySuffixService } from './possible-value-my-suffix.service';
import { IEligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';
import { EligibilityMySuffixService } from 'app/entities/eligibility-my-suffix';

@Component({
    selector: 'jhi-possible-value-my-suffix-update',
    templateUrl: './possible-value-my-suffix-update.component.html'
})
export class PossibleValueMySuffixUpdateComponent implements OnInit {
    possibleValue: IPossibleValueMySuffix;
    isSaving: boolean;

    eligibilities: IEligibilityMySuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected possibleValueService: PossibleValueMySuffixService,
        protected eligibilityService: EligibilityMySuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ possibleValue }) => {
            this.possibleValue = possibleValue;
        });
        this.eligibilityService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEligibilityMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEligibilityMySuffix[]>) => response.body)
            )
            .subscribe((res: IEligibilityMySuffix[]) => (this.eligibilities = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.possibleValue.id !== undefined) {
            this.subscribeToSaveResponse(this.possibleValueService.update(this.possibleValue));
        } else {
            this.subscribeToSaveResponse(this.possibleValueService.create(this.possibleValue));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPossibleValueMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPossibleValueMySuffix>) => this.onSaveSuccess(),
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

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEligibilityById(index: number, item: IEligibilityMySuffix) {
        return item.id;
    }
}
