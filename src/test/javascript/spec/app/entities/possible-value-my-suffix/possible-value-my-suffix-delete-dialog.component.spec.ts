/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { PossibleValueMySuffixDeleteDialogComponent } from 'app/entities/possible-value-my-suffix/possible-value-my-suffix-delete-dialog.component';
import { PossibleValueMySuffixService } from 'app/entities/possible-value-my-suffix/possible-value-my-suffix.service';

describe('Component Tests', () => {
    describe('PossibleValueMySuffix Management Delete Component', () => {
        let comp: PossibleValueMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PossibleValueMySuffixDeleteDialogComponent>;
        let service: PossibleValueMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [PossibleValueMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(PossibleValueMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PossibleValueMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PossibleValueMySuffixService);
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
