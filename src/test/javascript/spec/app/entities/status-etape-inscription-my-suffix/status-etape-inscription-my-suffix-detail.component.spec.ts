/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { StatusEtapeInscriptionMySuffixDetailComponent } from 'app/entities/status-etape-inscription-my-suffix/status-etape-inscription-my-suffix-detail.component';
import { StatusEtapeInscriptionMySuffix } from 'app/shared/model/status-etape-inscription-my-suffix.model';

describe('Component Tests', () => {
    describe('StatusEtapeInscriptionMySuffix Management Detail Component', () => {
        let comp: StatusEtapeInscriptionMySuffixDetailComponent;
        let fixture: ComponentFixture<StatusEtapeInscriptionMySuffixDetailComponent>;
        const route = ({ data: of({ statusEtapeInscription: new StatusEtapeInscriptionMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [StatusEtapeInscriptionMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StatusEtapeInscriptionMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StatusEtapeInscriptionMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.statusEtapeInscription).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
