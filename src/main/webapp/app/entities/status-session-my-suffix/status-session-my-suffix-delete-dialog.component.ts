import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStatusSessionMySuffix } from 'app/shared/model/status-session-my-suffix.model';
import { StatusSessionMySuffixService } from './status-session-my-suffix.service';

@Component({
    selector: 'jhi-status-session-my-suffix-delete-dialog',
    templateUrl: './status-session-my-suffix-delete-dialog.component.html'
})
export class StatusSessionMySuffixDeleteDialogComponent {
    statusSession: IStatusSessionMySuffix;

    constructor(
        protected statusSessionService: StatusSessionMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.statusSessionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'statusSessionListModification',
                content: 'Deleted an statusSession'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-status-session-my-suffix-delete-popup',
    template: ''
})
export class StatusSessionMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ statusSession }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StatusSessionMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.statusSession = statusSession;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/status-session-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/status-session-my-suffix', { outlets: { popup: null } }]);
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
