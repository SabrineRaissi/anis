/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EtudeUniversitaireMySuffixDetailComponent } from 'app/entities/etude-universitaire-my-suffix/etude-universitaire-my-suffix-detail.component';
import { EtudeUniversitaireMySuffix } from 'app/shared/model/etude-universitaire-my-suffix.model';

describe('Component Tests', () => {
    describe('EtudeUniversitaireMySuffix Management Detail Component', () => {
        let comp: EtudeUniversitaireMySuffixDetailComponent;
        let fixture: ComponentFixture<EtudeUniversitaireMySuffixDetailComponent>;
        const route = ({ data: of({ etudeUniversitaire: new EtudeUniversitaireMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EtudeUniversitaireMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EtudeUniversitaireMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EtudeUniversitaireMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.etudeUniversitaire).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
