import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPasswordResetTokenMySuffix } from 'app/shared/model/password-reset-token-my-suffix.model';

@Component({
    selector: 'jhi-password-reset-token-my-suffix-detail',
    templateUrl: './password-reset-token-my-suffix-detail.component.html'
})
export class PasswordResetTokenMySuffixDetailComponent implements OnInit {
    passwordResetToken: IPasswordResetTokenMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ passwordResetToken }) => {
            this.passwordResetToken = passwordResetToken;
        });
    }

    previousState() {
        window.history.back();
    }
}
