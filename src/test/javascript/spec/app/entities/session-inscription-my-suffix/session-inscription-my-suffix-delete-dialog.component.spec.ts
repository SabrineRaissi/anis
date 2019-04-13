/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { SessionInscriptionMySuffixDeleteDialogComponent } from 'app/entities/session-inscription-my-suffix/session-inscription-my-suffix-delete-dialog.component';
import { SessionInscriptionMySuffixService } from 'app/entities/session-inscription-my-suffix/session-inscription-my-suffix.service';

describe('Component Tests', () => {
    describe('SessionInscriptionMySuffix Management Delete Component', () => {
        let comp: SessionInscriptionMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<SessionInscriptionMySuffixDeleteDialogComponent>;
        let service: SessionInscriptionMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [SessionInscriptionMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(SessionInscriptionMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SessionInscriptionMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SessionInscriptionMySuffixService);
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
