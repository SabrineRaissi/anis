/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { PasswordResetTokenMySuffixDeleteDialogComponent } from 'app/entities/password-reset-token-my-suffix/password-reset-token-my-suffix-delete-dialog.component';
import { PasswordResetTokenMySuffixService } from 'app/entities/password-reset-token-my-suffix/password-reset-token-my-suffix.service';

describe('Component Tests', () => {
    describe('PasswordResetTokenMySuffix Management Delete Component', () => {
        let comp: PasswordResetTokenMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PasswordResetTokenMySuffixDeleteDialogComponent>;
        let service: PasswordResetTokenMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [PasswordResetTokenMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(PasswordResetTokenMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PasswordResetTokenMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PasswordResetTokenMySuffixService);
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
