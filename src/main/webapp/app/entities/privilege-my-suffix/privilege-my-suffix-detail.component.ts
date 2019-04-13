import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrivilegeMySuffix } from 'app/shared/model/privilege-my-suffix.model';

@Component({
    selector: 'jhi-privilege-my-suffix-detail',
    templateUrl: './privilege-my-suffix-detail.component.html'
})
export class PrivilegeMySuffixDetailComponent implements OnInit {
    privilege: IPrivilegeMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ privilege }) => {
            this.privilege = privilege;
        });
    }

    previousState() {
        window.history.back();
    }
}
