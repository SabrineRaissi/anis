/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { StatusInscriptionMySuffixDetailComponent } from 'app/entities/status-inscription-my-suffix/status-inscription-my-suffix-detail.component';
import { StatusInscriptionMySuffix } from 'app/shared/model/status-inscription-my-suffix.model';

describe('Component Tests', () => {
    describe('StatusInscriptionMySuffix Management Detail Component', () => {
        let comp: StatusInscriptionMySuffixDetailComponent;
        let fixture: ComponentFixture<StatusInscriptionMySuffixDetailComponent>;
        const route = ({ data: of({ statusInscription: new StatusInscriptionMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [StatusInscriptionMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StatusInscriptionMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StatusInscriptionMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.statusInscription).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
