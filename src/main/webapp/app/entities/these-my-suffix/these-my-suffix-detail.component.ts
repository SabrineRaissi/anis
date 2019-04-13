import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITheseMySuffix } from 'app/shared/model/these-my-suffix.model';

@Component({
    selector: 'jhi-these-my-suffix-detail',
    templateUrl: './these-my-suffix-detail.component.html'
})
export class TheseMySuffixDetailComponent implements OnInit {
    these: ITheseMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ these }) => {
            this.these = these;
        });
    }

    previousState() {
        window.history.back();
    }
}
