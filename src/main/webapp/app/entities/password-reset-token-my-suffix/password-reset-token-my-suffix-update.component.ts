import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IPasswordResetTokenMySuffix } from 'app/shared/model/password-reset-token-my-suffix.model';
import { PasswordResetTokenMySuffixService } from './password-reset-token-my-suffix.service';

@Component({
    selector: 'jhi-password-reset-token-my-suffix-update',
    templateUrl: './password-reset-token-my-suffix-update.component.html'
})
export class PasswordResetTokenMySuffixUpdateComponent implements OnInit {
    passwordResetToken: IPasswordResetTokenMySuffix;
    isSaving: boolean;
    expiryDate: string;

    constructor(protected passwordResetTokenService: PasswordResetTokenMySuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ passwordResetToken }) => {
            this.passwordResetToken = passwordResetToken;
            this.expiryDate =
                this.passwordResetToken.expiryDate != null ? this.passwordResetToken.expiryDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.passwordResetToken.expiryDate = this.expiryDate != null ? moment(this.expiryDate, DATE_TIME_FORMAT) : null;
        if (this.passwordResetToken.id !== undefined) {
            this.subscribeToSaveResponse(this.passwordResetTokenService.update(this.passwordResetToken));
        } else {
            this.subscribeToSaveResponse(this.passwordResetTokenService.create(this.passwordResetToken));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPasswordResetTokenMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPasswordResetTokenMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
