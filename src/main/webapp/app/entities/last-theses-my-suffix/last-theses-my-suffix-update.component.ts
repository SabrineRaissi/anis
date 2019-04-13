import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ILastThesesMySuffix } from 'app/shared/model/last-theses-my-suffix.model';
import { LastThesesMySuffixService } from './last-theses-my-suffix.service';
import { IDoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';
import { DoctorantMySuffixService } from 'app/entities/doctorant-my-suffix';

@Component({
    selector: 'jhi-last-theses-my-suffix-update',
    templateUrl: './last-theses-my-suffix-update.component.html'
})
export class LastThesesMySuffixUpdateComponent implements OnInit {
    lastTheses: ILastThesesMySuffix;
    isSaving: boolean;

    doctorants: IDoctorantMySuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected lastThesesService: LastThesesMySuffixService,
        protected doctorantService: DoctorantMySuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ lastTheses }) => {
            this.lastTheses = lastTheses;
        });
        this.doctorantService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IDoctorantMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDoctorantMySuffix[]>) => response.body)
            )
            .subscribe((res: IDoctorantMySuffix[]) => (this.doctorants = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.lastTheses.id !== undefined) {
            this.subscribeToSaveResponse(this.lastThesesService.update(this.lastTheses));
        } else {
            this.subscribeToSaveResponse(this.lastThesesService.create(this.lastTheses));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILastThesesMySuffix>>) {
        result.subscribe((res: HttpResponse<ILastThesesMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDoctorantById(index: number, item: IDoctorantMySuffix) {
        return item.id;
    }
}
