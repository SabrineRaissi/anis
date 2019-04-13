/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { DoctorantMySuffixDetailComponent } from 'app/entities/doctorant-my-suffix/doctorant-my-suffix-detail.component';
import { DoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';

describe('Component Tests', () => {
    describe('DoctorantMySuffix Management Detail Component', () => {
        let comp: DoctorantMySuffixDetailComponent;
        let fixture: ComponentFixture<DoctorantMySuffixDetailComponent>;
        const route = ({ data: of({ doctorant: new DoctorantMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [DoctorantMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DoctorantMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DoctorantMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.doctorant).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
