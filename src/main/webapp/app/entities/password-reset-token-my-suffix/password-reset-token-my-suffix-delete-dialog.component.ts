import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPasswordResetTokenMySuffix } from 'app/shared/model/password-reset-token-my-suffix.model';
import { PasswordResetTokenMySuffixService } from './password-reset-token-my-suffix.service';

@Component({
    selector: 'jhi-password-reset-token-my-suffix-delete-dialog',
    templateUrl: './password-reset-token-my-suffix-delete-dialog.component.html'
})
export class PasswordResetTokenMySuffixDeleteDialogComponent {
    passwordResetToken: IPasswordResetTokenMySuffix;

    constructor(
        protected passwordResetTokenService: PasswordResetTokenMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.passwordResetTokenService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'passwordResetTokenListModification',
                content: 'Deleted an passwordResetToken'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-password-reset-token-my-suffix-delete-popup',
    template: ''
})
export class PasswordResetTokenMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ passwordResetToken }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PasswordResetTokenMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.passwordResetToken = passwordResetToken;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/password-reset-token-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/password-reset-token-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
