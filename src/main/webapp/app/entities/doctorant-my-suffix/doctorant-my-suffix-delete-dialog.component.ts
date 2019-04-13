import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';
import { DoctorantMySuffixService } from './doctorant-my-suffix.service';

@Component({
    selector: 'jhi-doctorant-my-suffix-delete-dialog',
    templateUrl: './doctorant-my-suffix-delete-dialog.component.html'
})
export class DoctorantMySuffixDeleteDialogComponent {
    doctorant: IDoctorantMySuffix;

    constructor(
        protected doctorantService: DoctorantMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.doctorantService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'doctorantListModification',
                content: 'Deleted an doctorant'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-doctorant-my-suffix-delete-popup',
    template: ''
})
export class DoctorantMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ doctorant }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DoctorantMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.doctorant = doctorant;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/doctorant-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/doctorant-my-suffix', { outlets: { popup: null } }]);
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
