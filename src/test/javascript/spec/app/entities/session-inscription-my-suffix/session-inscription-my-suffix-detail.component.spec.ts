/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { SessionInscriptionMySuffixDetailComponent } from 'app/entities/session-inscription-my-suffix/session-inscription-my-suffix-detail.component';
import { SessionInscriptionMySuffix } from 'app/shared/model/session-inscription-my-suffix.model';

describe('Component Tests', () => {
    describe('SessionInscriptionMySuffix Management Detail Component', () => {
        let comp: SessionInscriptionMySuffixDetailComponent;
        let fixture: ComponentFixture<SessionInscriptionMySuffixDetailComponent>;
        const route = ({ data: of({ sessionInscription: new SessionInscriptionMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [SessionInscriptionMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SessionInscriptionMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SessionInscriptionMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sessionInscription).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
