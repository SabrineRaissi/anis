/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { LastThesesMySuffixDeleteDialogComponent } from 'app/entities/last-theses-my-suffix/last-theses-my-suffix-delete-dialog.component';
import { LastThesesMySuffixService } from 'app/entities/last-theses-my-suffix/last-theses-my-suffix.service';

describe('Component Tests', () => {
    describe('LastThesesMySuffix Management Delete Component', () => {
        let comp: LastThesesMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<LastThesesMySuffixDeleteDialogComponent>;
        let service: LastThesesMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [LastThesesMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(LastThesesMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LastThesesMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LastThesesMySuffixService);
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
