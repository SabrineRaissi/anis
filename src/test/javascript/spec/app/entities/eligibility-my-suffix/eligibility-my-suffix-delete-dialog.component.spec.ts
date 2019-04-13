/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EligibilityMySuffixDeleteDialogComponent } from 'app/entities/eligibility-my-suffix/eligibility-my-suffix-delete-dialog.component';
import { EligibilityMySuffixService } from 'app/entities/eligibility-my-suffix/eligibility-my-suffix.service';

describe('Component Tests', () => {
    describe('EligibilityMySuffix Management Delete Component', () => {
        let comp: EligibilityMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<EligibilityMySuffixDeleteDialogComponent>;
        let service: EligibilityMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EligibilityMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(EligibilityMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EligibilityMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EligibilityMySuffixService);
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
