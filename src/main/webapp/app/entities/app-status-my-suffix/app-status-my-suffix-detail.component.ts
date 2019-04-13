import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAppStatusMySuffix } from 'app/shared/model/app-status-my-suffix.model';

@Component({
    selector: 'jhi-app-status-my-suffix-detail',
    templateUrl: './app-status-my-suffix-detail.component.html'
})
export class AppStatusMySuffixDetailComponent implements OnInit {
    appStatus: IAppStatusMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ appStatus }) => {
            this.appStatus = appStatus;
        });
    }

    previousState() {
        window.history.back();
    }
}
