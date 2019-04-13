/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { PrivilegeMySuffixDetailComponent } from 'app/entities/privilege-my-suffix/privilege-my-suffix-detail.component';
import { PrivilegeMySuffix } from 'app/shared/model/privilege-my-suffix.model';

describe('Component Tests', () => {
    describe('PrivilegeMySuffix Management Detail Component', () => {
        let comp: PrivilegeMySuffixDetailComponent;
        let fixture: ComponentFixture<PrivilegeMySuffixDetailComponent>;
        const route = ({ data: of({ privilege: new PrivilegeMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [PrivilegeMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PrivilegeMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PrivilegeMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.privilege).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
