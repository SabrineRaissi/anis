import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatusSessionMySuffix } from 'app/shared/model/status-session-my-suffix.model';

@Component({
    selector: 'jhi-status-session-my-suffix-detail',
    templateUrl: './status-session-my-suffix-detail.component.html'
})
export class StatusSessionMySuffixDetailComponent implements OnInit {
    statusSession: IStatusSessionMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ statusSession }) => {
            this.statusSession = statusSession;
        });
    }

    previousState() {
        window.history.back();
    }
}
