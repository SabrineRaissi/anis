/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EligibilityMySuffixDetailComponent } from 'app/entities/eligibility-my-suffix/eligibility-my-suffix-detail.component';
import { EligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';

describe('Component Tests', () => {
    describe('EligibilityMySuffix Management Detail Component', () => {
        let comp: EligibilityMySuffixDetailComponent;
        let fixture: ComponentFixture<EligibilityMySuffixDetailComponent>;
        const route = ({ data: of({ eligibility: new EligibilityMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EligibilityMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EligibilityMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EligibilityMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.eligibility).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
