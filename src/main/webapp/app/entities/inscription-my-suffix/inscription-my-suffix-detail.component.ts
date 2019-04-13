import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInscriptionMySuffix } from 'app/shared/model/inscription-my-suffix.model';

@Component({
    selector: 'jhi-inscription-my-suffix-detail',
    templateUrl: './inscription-my-suffix-detail.component.html'
})
export class InscriptionMySuffixDetailComponent implements OnInit {
    inscription: IInscriptionMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ inscription }) => {
            this.inscription = inscription;
        });
    }

    previousState() {
        window.history.back();
    }
}
