/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EtapeInscriptionMySuffixDeleteDialogComponent } from 'app/entities/etape-inscription-my-suffix/etape-inscription-my-suffix-delete-dialog.component';
import { EtapeInscriptionMySuffixService } from 'app/entities/etape-inscription-my-suffix/etape-inscription-my-suffix.service';

describe('Component Tests', () => {
    describe('EtapeInscriptionMySuffix Management Delete Component', () => {
        let comp: EtapeInscriptionMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<EtapeInscriptionMySuffixDeleteDialogComponent>;
        let service: EtapeInscriptionMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EtapeInscriptionMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(EtapeInscriptionMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EtapeInscriptionMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EtapeInscriptionMySuffixService);
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
