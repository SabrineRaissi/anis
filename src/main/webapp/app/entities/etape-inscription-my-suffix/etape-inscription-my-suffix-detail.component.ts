import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtapeInscriptionMySuffix } from 'app/shared/model/etape-inscription-my-suffix.model';

@Component({
    selector: 'jhi-etape-inscription-my-suffix-detail',
    templateUrl: './etape-inscription-my-suffix-detail.component.html'
})
export class EtapeInscriptionMySuffixDetailComponent implements OnInit {
    etapeInscription: IEtapeInscriptionMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etapeInscription }) => {
            this.etapeInscription = etapeInscription;
        });
    }

    previousState() {
        window.history.back();
    }
}
