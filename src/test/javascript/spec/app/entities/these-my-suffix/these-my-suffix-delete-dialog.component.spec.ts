/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { TheseMySuffixDeleteDialogComponent } from 'app/entities/these-my-suffix/these-my-suffix-delete-dialog.component';
import { TheseMySuffixService } from 'app/entities/these-my-suffix/these-my-suffix.service';

describe('Component Tests', () => {
    describe('TheseMySuffix Management Delete Component', () => {
        let comp: TheseMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TheseMySuffixDeleteDialogComponent>;
        let service: TheseMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [TheseMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(TheseMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TheseMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TheseMySuffixService);
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
