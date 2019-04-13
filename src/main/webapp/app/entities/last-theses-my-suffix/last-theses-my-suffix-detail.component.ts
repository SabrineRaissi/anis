import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILastThesesMySuffix } from 'app/shared/model/last-theses-my-suffix.model';

@Component({
    selector: 'jhi-last-theses-my-suffix-detail',
    templateUrl: './last-theses-my-suffix-detail.component.html'
})
export class LastThesesMySuffixDetailComponent implements OnInit {
    lastTheses: ILastThesesMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ lastTheses }) => {
            this.lastTheses = lastTheses;
        });
    }

    previousState() {
        window.history.back();
    }
}
