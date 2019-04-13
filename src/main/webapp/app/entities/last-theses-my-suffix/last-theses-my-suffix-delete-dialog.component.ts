import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILastThesesMySuffix } from 'app/shared/model/last-theses-my-suffix.model';
import { LastThesesMySuffixService } from './last-theses-my-suffix.service';

@Component({
    selector: 'jhi-last-theses-my-suffix-delete-dialog',
    templateUrl: './last-theses-my-suffix-delete-dialog.component.html'
})
export class LastThesesMySuffixDeleteDialogComponent {
    lastTheses: ILastThesesMySuffix;

    constructor(
        protected lastThesesService: LastThesesMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.lastThesesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'lastThesesListModification',
                content: 'Deleted an lastTheses'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-last-theses-my-suffix-delete-popup',
    template: ''
})
export class LastThesesMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ lastTheses }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LastThesesMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.lastTheses = lastTheses;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/last-theses-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/last-theses-my-suffix', { outlets: { popup: null } }]);
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
