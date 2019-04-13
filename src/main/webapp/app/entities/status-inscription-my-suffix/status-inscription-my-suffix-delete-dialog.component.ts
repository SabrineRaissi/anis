import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStatusInscriptionMySuffix } from 'app/shared/model/status-inscription-my-suffix.model';
import { StatusInscriptionMySuffixService } from './status-inscription-my-suffix.service';

@Component({
    selector: 'jhi-status-inscription-my-suffix-delete-dialog',
    templateUrl: './status-inscription-my-suffix-delete-dialog.component.html'
})
export class StatusInscriptionMySuffixDeleteDialogComponent {
    statusInscription: IStatusInscriptionMySuffix;

    constructor(
        protected statusInscriptionService: StatusInscriptionMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.statusInscriptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'statusInscriptionListModification',
                content: 'Deleted an statusInscription'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-status-inscription-my-suffix-delete-popup',
    template: ''
})
export class StatusInscriptionMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ statusInscription }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StatusInscriptionMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.statusInscription = statusInscription;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/status-inscription-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/status-inscription-my-suffix', { outlets: { popup: null } }]);
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
