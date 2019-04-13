import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IInscriptionMySuffix } from 'app/shared/model/inscription-my-suffix.model';
import { InscriptionMySuffixService } from './inscription-my-suffix.service';
import { IDoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';
import { DoctorantMySuffixService } from 'app/entities/doctorant-my-suffix';
import { ITheseMySuffix } from 'app/shared/model/these-my-suffix.model';
import { TheseMySuffixService } from 'app/entities/these-my-suffix';
import { IStatusInscriptionMySuffix } from 'app/shared/model/status-inscription-my-suffix.model';
import { StatusInscriptionMySuffixService } from 'app/entities/status-inscription-my-suffix';
import { IEtapeInscriptionMySuffix } from 'app/shared/model/etape-inscription-my-suffix.model';
import { EtapeInscriptionMySuffixService } from 'app/entities/etape-inscription-my-suffix';

@Component({
    selector: 'jhi-inscription-my-suffix-update',
    templateUrl: './inscription-my-suffix-update.component.html'
})
export class InscriptionMySuffixUpdateComponent implements OnInit {
    inscription: IInscriptionMySuffix;
    isSaving: boolean;

    doctorants: IDoctorantMySuffix[];

    these: ITheseMySuffix[];

    statusinscriptions: IStatusInscriptionMySuffix[];

    nextsteps: IEtapeInscriptionMySuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected inscriptionService: InscriptionMySuffixService,
        protected doctorantService: DoctorantMySuffixService,
        protected theseService: TheseMySuffixService,
        protected statusInscriptionService: StatusInscriptionMySuffixService,
        protected etapeInscriptionService: EtapeInscriptionMySuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ inscription }) => {
            this.inscription = inscription;
        });
        this.doctorantService
            .query({ filter: 'inscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IDoctorantMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDoctorantMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IDoctorantMySuffix[]) => {
                    if (!this.inscription.doctorantId) {
                        this.doctorants = res;
                    } else {
                        this.doctorantService
                            .find(this.inscription.doctorantId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IDoctorantMySuffix>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IDoctorantMySuffix>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IDoctorantMySuffix) => (this.doctorants = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.theseService
            .query({ filter: 'inscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<ITheseMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITheseMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: ITheseMySuffix[]) => {
                    if (!this.inscription.theseId) {
                        this.these = res;
                    } else {
                        this.theseService
                            .find(this.inscription.theseId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<ITheseMySuffix>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<ITheseMySuffix>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: ITheseMySuffix) => (this.these = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.statusInscriptionService
            .query({ filter: 'inscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IStatusInscriptionMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IStatusInscriptionMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IStatusInscriptionMySuffix[]) => {
                    if (!this.inscription.statusInscriptionId) {
                        this.statusinscriptions = res;
                    } else {
                        this.statusInscriptionService
                            .find(this.inscription.statusInscriptionId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IStatusInscriptionMySuffix>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IStatusInscriptionMySuffix>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IStatusInscriptionMySuffix) => (this.statusinscriptions = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.etapeInscriptionService
            .query({ filter: 'inscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IEtapeInscriptionMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEtapeInscriptionMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IEtapeInscriptionMySuffix[]) => {
                    if (!this.inscription.nextStepId) {
                        this.nextsteps = res;
                    } else {
                        this.etapeInscriptionService
                            .find(this.inscription.nextStepId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IEtapeInscriptionMySuffix>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IEtapeInscriptionMySuffix>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IEtapeInscriptionMySuffix) => (this.nextsteps = [subRes].concat(res)),
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
        if (this.inscription.id !== undefined) {
            this.subscribeToSaveResponse(this.inscriptionService.update(this.inscription));
        } else {
            this.subscribeToSaveResponse(this.inscriptionService.create(this.inscription));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInscriptionMySuffix>>) {
        result.subscribe((res: HttpResponse<IInscriptionMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTheseById(index: number, item: ITheseMySuffix) {
        return item.id;
    }

    trackStatusInscriptionById(index: number, item: IStatusInscriptionMySuffix) {
        return item.id;
    }

    trackEtapeInscriptionById(index: number, item: IEtapeInscriptionMySuffix) {
        return item.id;
    }
}
