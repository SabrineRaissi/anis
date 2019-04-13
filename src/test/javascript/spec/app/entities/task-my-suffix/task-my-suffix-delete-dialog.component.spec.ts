/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { TaskMySuffixDeleteDialogComponent } from 'app/entities/task-my-suffix/task-my-suffix-delete-dialog.component';
import { TaskMySuffixService } from 'app/entities/task-my-suffix/task-my-suffix.service';

describe('Component Tests', () => {
    describe('TaskMySuffix Management Delete Component', () => {
        let comp: TaskMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TaskMySuffixDeleteDialogComponent>;
        let service: TaskMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [TaskMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(TaskMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TaskMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TaskMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});