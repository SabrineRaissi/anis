import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEtudeUniversitaireMySuffix } from 'app/shared/model/etude-universitaire-my-suffix.model';
import { EtudeUniversitaireMySuffixService } from './etude-universitaire-my-suffix.service';
import { IDoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';
import { DoctorantMySuffixService } from 'app/entities/doctorant-my-suffix';

@Component({
    selector: 'jhi-etude-universitaire-my-suffix-update',
    templateUrl: './etude-universitaire-my-suffix-update.component.html'
})
export class EtudeUniversitaireMySuffixUpdateComponent implements OnInit {
    etudeUniversitaire: IEtudeUniversitaireMySuffix;
    isSaving: boolean;

    doctorants: IDoctorantMySuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected etudeUniversitaireService: EtudeUniversitaireMySuffixService,
        protected doctorantService: DoctorantMySuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ etudeUniversitaire }) => {
            this.etudeUniversitaire = etudeUniversitaire;
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
        if (this.etudeUniversitaire.id !== undefined) {
            this.subscribeToSaveResponse(this.etudeUniversitaireService.update(this.etudeUniversitaire));
        } else {
            this.subscribeToSaveResponse(this.etudeUniversitaireService.create(this.etudeUniversitaire));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtudeUniversitaireMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEtudeUniversitaireMySuffix>) => this.onSaveSuccess(),
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

    trackDoctorantById(index: number, item: IDoctorantMySuffix) {
        return item.id;
    }
}
