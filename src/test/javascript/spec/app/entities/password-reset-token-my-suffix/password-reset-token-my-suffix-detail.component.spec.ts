/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { PasswordResetTokenMySuffixDetailComponent } from 'app/entities/password-reset-token-my-suffix/password-reset-token-my-suffix-detail.component';
import { PasswordResetTokenMySuffix } from 'app/shared/model/password-reset-token-my-suffix.model';

describe('Component Tests', () => {
    describe('PasswordResetTokenMySuffix Management Detail Component', () => {
        let comp: PasswordResetTokenMySuffixDetailComponent;
        let fixture: ComponentFixture<PasswordResetTokenMySuffixDetailComponent>;
        const route = ({ data: of({ passwordResetToken: new PasswordResetTokenMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [PasswordResetTokenMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PasswordResetTokenMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PasswordResetTokenMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.passwordResetToken).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
