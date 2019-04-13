import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ISessionInscriptionMySuffix } from 'app/shared/model/session-inscription-my-suffix.model';
import { SessionInscriptionMySuffixService } from './session-inscription-my-suffix.service';
import { ITheseMySuffix } from 'app/shared/model/these-my-suffix.model';
import { TheseMySuffixService } from 'app/entities/these-my-suffix';
import { IEtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';
import { EtablissementMySuffixService } from 'app/entities/etablissement-my-suffix';
import { IStatusSessionMySuffix } from 'app/shared/model/status-session-my-suffix.model';
import { StatusSessionMySuffixService } from 'app/entities/status-session-my-suffix';

@Component({
    selector: 'jhi-session-inscription-my-suffix-update',
    templateUrl: './session-inscription-my-suffix-update.component.html'
})
export class SessionInscriptionMySuffixUpdateComponent implements OnInit {
    sessionInscription: ISessionInscriptionMySuffix;
    isSaving: boolean;

    these: ITheseMySuffix[];

    etablissements: IEtablissementMySuffix[];

    statussessions: IStatusSessionMySuffix[];
    startDate: string;
    endDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sessionInscriptionService: SessionInscriptionMySuffixService,
        protected theseService: TheseMySuffixService,
        protected etablissementService: EtablissementMySuffixService,
        protected statusSessionService: StatusSessionMySuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sessionInscription }) => {
            this.sessionInscription = sessionInscription;
            this.startDate = this.sessionInscription.startDate != null ? this.sessionInscription.startDate.format(DATE_TIME_FORMAT) : null;
            this.endDate = this.sessionInscription.endDate != null ? this.sessionInscription.endDate.format(DATE_TIME_FORMAT) : null;
        });
        this.theseService
            .query({ filter: 'sessioninscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<ITheseMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITheseMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: ITheseMySuffix[]) => {
                    if (!this.sessionInscription.theseId) {
                        this.these = res;
                    } else {
                        this.theseService
                            .find(this.sessionInscription.theseId)
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
        this.etablissementService
            .query({ filter: 'sessioninscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IEtablissementMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEtablissementMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IEtablissementMySuffix[]) => {
                    if (!this.sessionInscription.etablissementId) {
                        this.etablissements = res;
                    } else {
                        this.etablissementService
                            .find(this.sessionInscription.etablissementId)
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
        this.statusSessionService
            .query({ filter: 'sessioninscription-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IStatusSessionMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IStatusSessionMySuffix[]>) => response.body)
            )
            .subscribe(
                (res: IStatusSessionMySuffix[]) => {
                    if (!this.sessionInscription.statusSessionId) {
                        this.statussessions = res;
                    } else {
                        this.statusSessionService
                            .find(this.sessionInscription.statusSessionId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IStatusSessionMySuffix>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IStatusSessionMySuffix>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IStatusSessionMySuffix) => (this.statussessions = [subRes].concat(res)),
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
        this.sessionInscription.startDate = this.startDate != null ? moment(this.startDate, DATE_TIME_FORMAT) : null;
        this.sessionInscription.endDate = this.endDate != null ? moment(this.endDate, DATE_TIME_FORMAT) : null;
        if (this.sessionInscription.id !== undefined) {
            this.subscribeToSaveResponse(this.sessionInscriptionService.update(this.sessionInscription));
        } else {
            this.subscribeToSaveResponse(this.sessionInscriptionService.create(this.sessionInscription));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISessionInscriptionMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<ISessionInscriptionMySuffix>) => this.onSaveSuccess(),
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

    trackTheseById(index: number, item: ITheseMySuffix) {
        return item.id;
    }

    trackEtablissementById(index: number, item: IEtablissementMySuffix) {
        return item.id;
    }

    trackStatusSessionById(index: number, item: IStatusSessionMySuffix) {
        return item.id;
    }
}
