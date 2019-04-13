import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAppStatusMySuffix } from 'app/shared/model/app-status-my-suffix.model';
import { AppStatusMySuffixService } from './app-status-my-suffix.service';

@Component({
    selector: 'jhi-app-status-my-suffix-delete-dialog',
    templateUrl: './app-status-my-suffix-delete-dialog.component.html'
})
export class AppStatusMySuffixDeleteDialogComponent {
    appStatus: IAppStatusMySuffix;

    constructor(
        protected appStatusService: AppStatusMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.appStatusService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'appStatusListModification',
                content: 'Deleted an appStatus'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-app-status-my-suffix-delete-popup',
    template: ''
})
export class AppStatusMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ appStatus }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AppStatusMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.appStatus = appStatus;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/app-status-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/app-status-my-suffix', { outlets: { popup: null } }]);
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
