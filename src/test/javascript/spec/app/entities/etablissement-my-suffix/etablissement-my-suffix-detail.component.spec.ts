/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EtablissementMySuffixDetailComponent } from 'app/entities/etablissement-my-suffix/etablissement-my-suffix-detail.component';
import { EtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';

describe('Component Tests', () => {
    describe('EtablissementMySuffix Management Detail Component', () => {
        let comp: EtablissementMySuffixDetailComponent;
        let fixture: ComponentFixture<EtablissementMySuffixDetailComponent>;
        const route = ({ data: of({ etablissement: new EtablissementMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EtablissementMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EtablissementMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EtablissementMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.etablissement).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
