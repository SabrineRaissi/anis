import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtapeInscriptionMySuffix } from 'app/shared/model/etape-inscription-my-suffix.model';
import { EtapeInscriptionMySuffixService } from './etape-inscription-my-suffix.service';

@Component({
    selector: 'jhi-etape-inscription-my-suffix-delete-dialog',
    templateUrl: './etape-inscription-my-suffix-delete-dialog.component.html'
})
export class EtapeInscriptionMySuffixDeleteDialogComponent {
    etapeInscription: IEtapeInscriptionMySuffix;

    constructor(
        protected etapeInscriptionService: EtapeInscriptionMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.etapeInscriptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'etapeInscriptionListModification',
                content: 'Deleted an etapeInscription'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-etape-inscription-my-suffix-delete-popup',
    template: ''
})
export class EtapeInscriptionMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etapeInscription }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EtapeInscriptionMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.etapeInscription = etapeInscription;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/etape-inscription-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/etape-inscription-my-suffix', { outlets: { popup: null } }]);
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
