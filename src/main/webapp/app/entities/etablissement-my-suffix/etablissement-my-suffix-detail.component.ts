import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';

@Component({
    selector: 'jhi-etablissement-my-suffix-detail',
    templateUrl: './etablissement-my-suffix-detail.component.html'
})
export class EtablissementMySuffixDetailComponent implements OnInit {
    etablissement: IEtablissementMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etablissement }) => {
            this.etablissement = etablissement;
        });
    }

    previousState() {
        window.history.back();
    }
}
