import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITheseMySuffix } from 'app/shared/model/these-my-suffix.model';
import { TheseMySuffixService } from './these-my-suffix.service';

@Component({
    selector: 'jhi-these-my-suffix-delete-dialog',
    templateUrl: './these-my-suffix-delete-dialog.component.html'
})
export class TheseMySuffixDeleteDialogComponent {
    these: ITheseMySuffix;

    constructor(
        protected theseService: TheseMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.theseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'theseListModification',
                content: 'Deleted an these'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-these-my-suffix-delete-popup',
    template: ''
})
export class TheseMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ these }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TheseMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.these = these;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/these-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/these-my-suffix', { outlets: { popup: null } }]);
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
