import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPossibleValueMySuffix } from 'app/shared/model/possible-value-my-suffix.model';

@Component({
    selector: 'jhi-possible-value-my-suffix-detail',
    templateUrl: './possible-value-my-suffix-detail.component.html'
})
export class PossibleValueMySuffixDetailComponent implements OnInit {
    possibleValue: IPossibleValueMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ possibleValue }) => {
            this.possibleValue = possibleValue;
        });
    }

    previousState() {
        window.history.back();
    }
}
