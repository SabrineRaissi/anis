import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEtapeInscriptionMySuffix } from 'app/shared/model/etape-inscription-my-suffix.model';
import { EtapeInscriptionMySuffixService } from './etape-inscription-my-suffix.service';
import { IStatusEtapeInscriptionMySuffix } from 'app/shared/model/status-etape-inscription-my-suffix.model';
import { StatusEtapeInscriptionMySuffixService } from 'app/entities/status-etape-inscription-my-suffix';
import { IEtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';
import { EtablissementMySuffixService } from 'app/entities/etablissement-my-suffix';

@Component({
    selector: 'jhi-etape-inscription-my-suffix-update',
    templateUrl: './etape-inscription-my-suffix-update.component.html'
})
export class EtapeInscriptionMySuffixUpdateComponent implements OnInit {
    etapeInscription: IEtapeInscriptionMySuffix;
    isSaving: boolean;

    nexts: IEtapeInscriptionMySuffix[];

    previous: IEtapeInscriptionMySuffix[];

    statusetapes: IStatusEtapeInscriptionMySuffix[];

    etablissements: IEtablissementMySuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected etapeInscriptionService: EtapeInscriptionMySuffixService,
        protected statusEtapeInscriptionService: StatusEtapeInscriptionMySuffixService,
        protected etablissementService: EtablissementMySuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ etapeInscription }) => {
            this.etapeInscription = etapeInscription;
        });
        this.etapeInscriptionService
            .query({ filter: 'etapeinscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IEtapeInscriptionMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEtapeInscriptionMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IEtapeInscriptionMySuffix[]) => {
                    if (!this.etapeInscription.nextId) {
                        this.nexts = res;
                    } else {
                        this.etapeInscriptionService
                            .find(this.etapeInscription.nextId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IEtapeInscriptionMySuffix>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IEtapeInscriptionMySuffix>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IEtapeInscriptionMySuffix) => (this.nexts = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.etapeInscriptionService
            .query({ filter: 'etapeinscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IEtapeInscriptionMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEtapeInscriptionMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IEtapeInscriptionMySuffix[]) => {
                    if (!this.etapeInscription.previousId) {
                        this.previous = res;
                    } else {
                        this.etapeInscriptionService
                            .find(this.etapeInscription.previousId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IEtapeInscriptionMySuffix>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IEtapeInscriptionMySuffix>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IEtapeInscriptionMySuffix) => (this.previous = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.statusEtapeInscriptionService
            .query({ filter: 'etapeinscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IStatusEtapeInscriptionMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IStatusEtapeInscriptionMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IStatusEtapeInscriptionMySuffix[]) => {
                    if (!this.etapeInscription.statusEtapeId) {
                        this.statusetapes = res;
                    } else {
                        this.statusEtapeInscriptionService
                            .find(this.etapeInscription.statusEtapeId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IStatusEtapeInscriptionMySuffix>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IStatusEtapeInscriptionMySuffix>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IStatusEtapeInscriptionMySuffix) => (this.statusetapes = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.etablissementService
            .query({ filter: 'etapeinscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IEtablissementMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEtablissementMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IEtablissementMySuffix[]) => {
                    if (!this.etapeInscription.etablissementId) {
                        this.etablissements = res;
                    } else {
                        this.etablissementService
                            .find(this.etapeInscription.etablissementId)
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
        if (this.etapeInscription.id !== undefined) {
            this.subscribeToSaveResponse(this.etapeInscriptionService.update(this.etapeInscription));
        } else {
            this.subscribeToSaveResponse(this.etapeInscriptionService.create(this.etapeInscription));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtapeInscriptionMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEtapeInscriptionMySuffix>) => this.onSaveSuccess(),
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

    trackEtapeInscriptionById(index: number, item: IEtapeInscriptionMySuffix) {
        return item.id;
    }

    trackStatusEtapeInscriptionById(index: number, item: IStatusEtapeInscriptionMySuffix) {
        return item.id;
    }

    trackEtablissementById(index: number, item: IEtablissementMySuffix) {
        return item.id;
    }
}
