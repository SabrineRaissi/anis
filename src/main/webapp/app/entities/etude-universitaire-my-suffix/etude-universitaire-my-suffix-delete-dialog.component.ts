import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtudeUniversitaireMySuffix } from 'app/shared/model/etude-universitaire-my-suffix.model';
import { EtudeUniversitaireMySuffixService } from './etude-universitaire-my-suffix.service';

@Component({
    selector: 'jhi-etude-universitaire-my-suffix-delete-dialog',
    templateUrl: './etude-universitaire-my-suffix-delete-dialog.component.html'
})
export class EtudeUniversitaireMySuffixDeleteDialogComponent {
    etudeUniversitaire: IEtudeUniversitaireMySuffix;

    constructor(
        protected etudeUniversitaireService: EtudeUniversitaireMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.etudeUniversitaireService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'etudeUniversitaireListModification',
                content: 'Deleted an etudeUniversitaire'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-etude-universitaire-my-suffix-delete-popup',
    template: ''
})
export class EtudeUniversitaireMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etudeUniversitaire }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EtudeUniversitaireMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.etudeUniversitaire = etudeUniversitaire;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/etude-universitaire-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/etude-universitaire-my-suffix', { outlets: { popup: null } }]);
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
