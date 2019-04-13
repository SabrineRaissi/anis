import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';
import { EligibilityMySuffixService } from './eligibility-my-suffix.service';

@Component({
    selector: 'jhi-eligibility-my-suffix-delete-dialog',
    templateUrl: './eligibility-my-suffix-delete-dialog.component.html'
})
export class EligibilityMySuffixDeleteDialogComponent {
    eligibility: IEligibilityMySuffix;

    constructor(
        protected eligibilityService: EligibilityMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.eligibilityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'eligibilityListModification',
                content: 'Deleted an eligibility'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-eligibility-my-suffix-delete-popup',
    template: ''
})
export class EligibilityMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eligibility }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EligibilityMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.eligibility = eligibility;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/eligibility-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/eligibility-my-suffix', { outlets: { popup: null } }]);
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
