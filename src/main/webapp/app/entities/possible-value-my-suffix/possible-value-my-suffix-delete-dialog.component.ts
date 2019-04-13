import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPossibleValueMySuffix } from 'app/shared/model/possible-value-my-suffix.model';
import { PossibleValueMySuffixService } from './possible-value-my-suffix.service';

@Component({
    selector: 'jhi-possible-value-my-suffix-delete-dialog',
    templateUrl: './possible-value-my-suffix-delete-dialog.component.html'
})
export class PossibleValueMySuffixDeleteDialogComponent {
    possibleValue: IPossibleValueMySuffix;

    constructor(
        protected possibleValueService: PossibleValueMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.possibleValueService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'possibleValueListModification',
                content: 'Deleted an possibleValue'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-possible-value-my-suffix-delete-popup',
    template: ''
})
export class PossibleValueMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ possibleValue }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PossibleValueMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.possibleValue = possibleValue;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/possible-value-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/possible-value-my-suffix', { outlets: { popup: null } }]);
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
