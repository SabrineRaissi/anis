import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStatusEtapeInscriptionMySuffix } from 'app/shared/model/status-etape-inscription-my-suffix.model';
import { StatusEtapeInscriptionMySuffixService } from './status-etape-inscription-my-suffix.service';

@Component({
    selector: 'jhi-status-etape-inscription-my-suffix-delete-dialog',
    templateUrl: './status-etape-inscription-my-suffix-delete-dialog.component.html'
})
export class StatusEtapeInscriptionMySuffixDeleteDialogComponent {
    statusEtapeInscription: IStatusEtapeInscriptionMySuffix;

    constructor(
        protected statusEtapeInscriptionService: StatusEtapeInscriptionMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.statusEtapeInscriptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'statusEtapeInscriptionListModification',
                content: 'Deleted an statusEtapeInscription'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-status-etape-inscription-my-suffix-delete-popup',
    template: ''
})
export class StatusEtapeInscriptionMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ statusEtapeInscription }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StatusEtapeInscriptionMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.statusEtapeInscription = statusEtapeInscription;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/status-etape-inscription-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/status-etape-inscription-my-suffix', { outlets: { popup: null } }]);
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
