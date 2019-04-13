import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtudeUniversitaireMySuffix } from 'app/shared/model/etude-universitaire-my-suffix.model';

@Component({
    selector: 'jhi-etude-universitaire-my-suffix-detail',
    templateUrl: './etude-universitaire-my-suffix-detail.component.html'
})
export class EtudeUniversitaireMySuffixDetailComponent implements OnInit {
    etudeUniversitaire: IEtudeUniversitaireMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etudeUniversitaire }) => {
            this.etudeUniversitaire = etudeUniversitaire;
        });
    }

    previousState() {
        window.history.back();
    }
}
