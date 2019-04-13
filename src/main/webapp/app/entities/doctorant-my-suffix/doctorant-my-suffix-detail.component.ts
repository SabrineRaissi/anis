import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IDoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';

@Component({
    selector: 'jhi-doctorant-my-suffix-detail',
    templateUrl: './doctorant-my-suffix-detail.component.html'
})
export class DoctorantMySuffixDetailComponent implements OnInit {
    doctorant: IDoctorantMySuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ doctorant }) => {
            this.doctorant = doctorant;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
