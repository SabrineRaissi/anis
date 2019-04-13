import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrivilegeMySuffix } from 'app/shared/model/privilege-my-suffix.model';
import { PrivilegeMySuffixService } from './privilege-my-suffix.service';

@Component({
    selector: 'jhi-privilege-my-suffix-delete-dialog',
    templateUrl: './privilege-my-suffix-delete-dialog.component.html'
})
export class PrivilegeMySuffixDeleteDialogComponent {
    privilege: IPrivilegeMySuffix;

    constructor(
        protected privilegeService: PrivilegeMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.privilegeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'privilegeListModification',
                content: 'Deleted an privilege'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-privilege-my-suffix-delete-popup',
    template: ''
})
export class PrivilegeMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ privilege }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PrivilegeMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.privilege = privilege;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/privilege-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/privilege-my-suffix', { outlets: { popup: null } }]);
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
