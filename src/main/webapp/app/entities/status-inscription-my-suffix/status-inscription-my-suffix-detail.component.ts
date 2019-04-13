import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatusInscriptionMySuffix } from 'app/shared/model/status-inscription-my-suffix.model';

@Component({
    selector: 'jhi-status-inscription-my-suffix-detail',
    templateUrl: './status-inscription-my-suffix-detail.component.html'
})
export class StatusInscriptionMySuffixDetailComponent implements OnInit {
    statusInscription: IStatusInscriptionMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ statusInscription }) => {
            this.statusInscription = statusInscription;
        });
    }

    previousState() {
        window.history.back();
    }
}
