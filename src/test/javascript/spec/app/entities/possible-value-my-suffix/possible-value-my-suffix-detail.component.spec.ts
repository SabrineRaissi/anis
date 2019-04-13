/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { PossibleValueMySuffixDetailComponent } from 'app/entities/possible-value-my-suffix/possible-value-my-suffix-detail.component';
import { PossibleValueMySuffix } from 'app/shared/model/possible-value-my-suffix.model';

describe('Component Tests', () => {
    describe('PossibleValueMySuffix Management Detail Component', () => {
        let comp: PossibleValueMySuffixDetailComponent;
        let fixture: ComponentFixture<PossibleValueMySuffixDetailComponent>;
        const route = ({ data: of({ possibleValue: new PossibleValueMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [PossibleValueMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PossibleValueMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PossibleValueMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.possibleValue).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
