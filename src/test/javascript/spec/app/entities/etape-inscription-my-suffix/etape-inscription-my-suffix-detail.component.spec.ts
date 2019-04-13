/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EtapeInscriptionMySuffixDetailComponent } from 'app/entities/etape-inscription-my-suffix/etape-inscription-my-suffix-detail.component';
import { EtapeInscriptionMySuffix } from 'app/shared/model/etape-inscription-my-suffix.model';

describe('Component Tests', () => {
    describe('EtapeInscriptionMySuffix Management Detail Component', () => {
        let comp: EtapeInscriptionMySuffixDetailComponent;
        let fixture: ComponentFixture<EtapeInscriptionMySuffixDetailComponent>;
        const route = ({ data: of({ etapeInscription: new EtapeInscriptionMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EtapeInscriptionMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EtapeInscriptionMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EtapeInscriptionMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.etapeInscription).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
