/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { StatusEtapeInscriptionMySuffixDeleteDialogComponent } from 'app/entities/status-etape-inscription-my-suffix/status-etape-inscription-my-suffix-delete-dialog.component';
import { StatusEtapeInscriptionMySuffixService } from 'app/entities/status-etape-inscription-my-suffix/status-etape-inscription-my-suffix.service';

describe('Component Tests', () => {
    describe('StatusEtapeInscriptionMySuffix Management Delete Component', () => {
        let comp: StatusEtapeInscriptionMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<StatusEtapeInscriptionMySuffixDeleteDialogComponent>;
        let service: StatusEtapeInscriptionMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [StatusEtapeInscriptionMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(StatusEtapeInscriptionMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StatusEtapeInscriptionMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StatusEtapeInscriptionMySuffixService);
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
