import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';
import { EtablissementMySuffixService } from './etablissement-my-suffix.service';

@Component({
    selector: 'jhi-etablissement-my-suffix-delete-dialog',
    templateUrl: './etablissement-my-suffix-delete-dialog.component.html'
})
export class EtablissementMySuffixDeleteDialogComponent {
    etablissement: IEtablissementMySuffix;

    constructor(
        protected etablissementService: EtablissementMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.etablissementService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'etablissementListModification',
                content: 'Deleted an etablissement'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-etablissement-my-suffix-delete-popup',
    template: ''
})
export class EtablissementMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etablissement }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EtablissementMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.etablissement = etablissement;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/etablissement-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/etablissement-my-suffix', { outlets: { popup: null } }]);
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
