import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInscriptionMySuffix } from 'app/shared/model/inscription-my-suffix.model';
import { InscriptionMySuffixService } from './inscription-my-suffix.service';

@Component({
    selector: 'jhi-inscription-my-suffix-delete-dialog',
    templateUrl: './inscription-my-suffix-delete-dialog.component.html'
})
export class InscriptionMySuffixDeleteDialogComponent {
    inscription: IInscriptionMySuffix;

    constructor(
        protected inscriptionService: InscriptionMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.inscriptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'inscriptionListModification',
                content: 'Deleted an inscription'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-inscription-my-suffix-delete-popup',
    template: ''
})
export class InscriptionMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ inscription }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InscriptionMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.inscription = inscription;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/inscription-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/inscription-my-suffix', { outlets: { popup: null } }]);
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
