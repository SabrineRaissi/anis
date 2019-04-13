/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { TheseMySuffixDetailComponent } from 'app/entities/these-my-suffix/these-my-suffix-detail.component';
import { TheseMySuffix } from 'app/shared/model/these-my-suffix.model';

describe('Component Tests', () => {
    describe('TheseMySuffix Management Detail Component', () => {
        let comp: TheseMySuffixDetailComponent;
        let fixture: ComponentFixture<TheseMySuffixDetailComponent>;
        const route = ({ data: of({ these: new TheseMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [TheseMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TheseMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TheseMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.these).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
