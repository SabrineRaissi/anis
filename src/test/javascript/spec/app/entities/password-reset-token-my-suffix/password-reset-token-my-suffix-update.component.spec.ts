/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { PasswordResetTokenMySuffixUpdateComponent } from 'app/entities/password-reset-token-my-suffix/password-reset-token-my-suffix-update.component';
import { PasswordResetTokenMySuffixService } from 'app/entities/password-reset-token-my-suffix/password-reset-token-my-suffix.service';
import { PasswordResetTokenMySuffix } from 'app/shared/model/password-reset-token-my-suffix.model';

describe('Component Tests', () => {
    describe('PasswordResetTokenMySuffix Management Update Component', () => {
        let comp: PasswordResetTokenMySuffixUpdateComponent;
        let fixture: ComponentFixture<PasswordResetTokenMySuffixUpdateComponent>;
        let service: PasswordResetTokenMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [PasswordResetTokenMySuffixUpdateComponent]
            })
                .overrideTemplate(PasswordResetTokenMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PasswordResetTokenMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PasswordResetTokenMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PasswordResetTokenMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.passwordResetToken = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PasswordResetTokenMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.passwordResetToken = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
