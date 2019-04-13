/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { InscriptionMySuffixDetailComponent } from 'app/entities/inscription-my-suffix/inscription-my-suffix-detail.component';
import { InscriptionMySuffix } from 'app/shared/model/inscription-my-suffix.model';

describe('Component Tests', () => {
    describe('InscriptionMySuffix Management Detail Component', () => {
        let comp: InscriptionMySuffixDetailComponent;
        let fixture: ComponentFixture<InscriptionMySuffixDetailComponent>;
        const route = ({ data: of({ inscription: new InscriptionMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [InscriptionMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InscriptionMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InscriptionMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.inscription).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
