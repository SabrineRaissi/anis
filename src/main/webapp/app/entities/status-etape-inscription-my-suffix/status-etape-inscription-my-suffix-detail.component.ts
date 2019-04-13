import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatusEtapeInscriptionMySuffix } from 'app/shared/model/status-etape-inscription-my-suffix.model';

@Component({
    selector: 'jhi-status-etape-inscription-my-suffix-detail',
    templateUrl: './status-etape-inscription-my-suffix-detail.component.html'
})
export class StatusEtapeInscriptionMySuffixDetailComponent implements OnInit {
    statusEtapeInscription: IStatusEtapeInscriptionMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ statusEtapeInscription }) => {
            this.statusEtapeInscription = statusEtapeInscription;
        });
    }

    previousState() {
        window.history.back();
    }
}
