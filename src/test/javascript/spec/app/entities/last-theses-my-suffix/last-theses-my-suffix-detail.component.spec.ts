/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { LastThesesMySuffixDetailComponent } from 'app/entities/last-theses-my-suffix/last-theses-my-suffix-detail.component';
import { LastThesesMySuffix } from 'app/shared/model/last-theses-my-suffix.model';

describe('Component Tests', () => {
    describe('LastThesesMySuffix Management Detail Component', () => {
        let comp: LastThesesMySuffixDetailComponent;
        let fixture: ComponentFixture<LastThesesMySuffixDetailComponent>;
        const route = ({ data: of({ lastTheses: new LastThesesMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [LastThesesMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LastThesesMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LastThesesMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.lastTheses).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
