/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { StatusSessionMySuffixDeleteDialogComponent } from 'app/entities/status-session-my-suffix/status-session-my-suffix-delete-dialog.component';
import { StatusSessionMySuffixService } from 'app/entities/status-session-my-suffix/status-session-my-suffix.service';

describe('Component Tests', () => {
    describe('StatusSessionMySuffix Management Delete Component', () => {
        let comp: StatusSessionMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<StatusSessionMySuffixDeleteDialogComponent>;
        let service: StatusSessionMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [StatusSessionMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(StatusSessionMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StatusSessionMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StatusSessionMySuffixService);
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
