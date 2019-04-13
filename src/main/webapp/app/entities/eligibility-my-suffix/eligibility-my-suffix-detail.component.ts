import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';

@Component({
    selector: 'jhi-eligibility-my-suffix-detail',
    templateUrl: './eligibility-my-suffix-detail.component.html'
})
export class EligibilityMySuffixDetailComponent implements OnInit {
    eligibility: IEligibilityMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eligibility }) => {
            this.eligibility = eligibility;
        });
    }

    previousState() {
        window.history.back();
    }
}
