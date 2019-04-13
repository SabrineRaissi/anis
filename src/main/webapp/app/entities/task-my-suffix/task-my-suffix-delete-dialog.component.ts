import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITaskMySuffix } from 'app/shared/model/task-my-suffix.model';
import { TaskMySuffixService } from './task-my-suffix.service';

@Component({
    selector: 'jhi-task-my-suffix-delete-dialog',
    templateUrl: './task-my-suffix-delete-dialog.component.html'
})
export class TaskMySuffixDeleteDialogComponent {
    task: ITaskMySuffix;

    constructor(protected taskService: TaskMySuffixService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.taskService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'taskListModification',
                content: 'Deleted an task'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-task-my-suffix-delete-popup',
    template: ''
})
export class TaskMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ task }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TaskMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.task = task;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/task-my-suffix', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/task-my-suffix', { outlets: { popup: null } }]);
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
