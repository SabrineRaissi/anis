import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeviceMetadataMySuffix } from 'app/shared/model/device-metadata-my-suffix.model';
import { DeviceMetadataMySuffixService } from './device-metadata-my-suffix.service';

@Component({
    selector: 'jhi-device-metadata-my-suffix-delete-dialog',
    templateUrl: './device-metadata-my-suffix-delete-dialog.component.html'
})
export class DeviceMetadataMySuffixDeleteDialogComponent {
    deviceMetadata: IDeviceMetadataMySuffix;

    constructor(
        protected deviceMetadataService: DeviceMetadataMySuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.deviceMetadataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'deviceMetadataListModification',
                content: 'Deleted an deviceMetadata'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-device-metadata-my-suffix-delete-popup',
    template: ''
})
export class DeviceMetadataMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deviceMetadata }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DeviceMetadataMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.deviceMetadata = deviceMetadata;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/device-metadata-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/device-metadata-my-suffix', { outlets: { popup: null } }]);
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
