import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISessionInscriptionMySuffix } from 'app/shared/model/session-inscription-my-suffix.model';

@Component({
    selector: 'jhi-session-inscription-my-suffix-detail',
    templateUrl: './session-inscription-my-suffix-detail.component.html'
})
export class SessionInscriptionMySuffixDetailComponent implements OnInit {
    sessionInscription: ISessionInscriptionMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sessionInscription }) => {
            this.sessionInscription = sessionInscription;
        });
    }

    previousState() {
        window.history.back();
    }
}
