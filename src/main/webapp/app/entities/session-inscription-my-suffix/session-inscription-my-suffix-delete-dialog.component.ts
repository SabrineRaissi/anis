import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISessionInscriptionMySuffix } from 'app/shared/model/session-inscription-my-suffix.model';
import { SessionInscriptionMySuffixService } from './session-inscription-my-suffix.service';

@Component({
    selector: 'jhi-session-inscription-my-suffix-delete-dialog',
    templateUrl: './session-inscription-my-suffix-delete-dialog.component.html'
})
export class SessionInscriptionMySuffixDeleteDialogComponent {
    sessionInscription: ISessionInscriptionMySuffix;

    constructor(
        protected sessionInscriptionService: SessionInscriptionMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sessionInscriptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sessionInscriptionListModification',
                content: 'Deleted an sessionInscription'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-session-inscription-my-suffix-delete-popup',
    template: ''
})
export class SessionInscriptionMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sessionInscription }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SessionInscriptionMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sessionInscription = sessionInscription;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/session-inscription-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/session-inscription-my-suffix', { outlets: { popup: null } }]);
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
