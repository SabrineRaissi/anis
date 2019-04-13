/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { InscriptionMySuffixDeleteDialogComponent } from 'app/entities/inscription-my-suffix/inscription-my-suffix-delete-dialog.component';
import { InscriptionMySuffixService } from 'app/entities/inscription-my-suffix/inscription-my-suffix.service';

describe('Component Tests', () => {
    describe('InscriptionMySuffix Management Delete Component', () => {
        let comp: InscriptionMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<InscriptionMySuffixDeleteDialogComponent>;
        let service: InscriptionMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [InscriptionMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(InscriptionMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InscriptionMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InscriptionMySuffixService);
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
