/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { PrivilegeMySuffixDeleteDialogComponent } from 'app/entities/privilege-my-suffix/privilege-my-suffix-delete-dialog.component';
import { PrivilegeMySuffixService } from 'app/entities/privilege-my-suffix/privilege-my-suffix.service';

describe('Component Tests', () => {
    describe('PrivilegeMySuffix Management Delete Component', () => {
        let comp: PrivilegeMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PrivilegeMySuffixDeleteDialogComponent>;
        let service: PrivilegeMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [PrivilegeMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(PrivilegeMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PrivilegeMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PrivilegeMySuffixService);
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
