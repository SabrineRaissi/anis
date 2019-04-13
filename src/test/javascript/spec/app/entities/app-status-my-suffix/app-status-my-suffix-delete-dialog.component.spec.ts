/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { AppStatusMySuffixDeleteDialogComponent } from 'app/entities/app-status-my-suffix/app-status-my-suffix-delete-dialog.component';
import { AppStatusMySuffixService } from 'app/entities/app-status-my-suffix/app-status-my-suffix.service';

describe('Component Tests', () => {
    describe('AppStatusMySuffix Management Delete Component', () => {
        let comp: AppStatusMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<AppStatusMySuffixDeleteDialogComponent>;
        let service: AppStatusMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [AppStatusMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(AppStatusMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AppStatusMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppStatusMySuffixService);
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
