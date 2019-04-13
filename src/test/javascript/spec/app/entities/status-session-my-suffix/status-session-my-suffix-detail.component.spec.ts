/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { StatusSessionMySuffixDetailComponent } from 'app/entities/status-session-my-suffix/status-session-my-suffix-detail.component';
import { StatusSessionMySuffix } from 'app/shared/model/status-session-my-suffix.model';

describe('Component Tests', () => {
    describe('StatusSessionMySuffix Management Detail Component', () => {
        let comp: StatusSessionMySuffixDetailComponent;
        let fixture: ComponentFixture<StatusSessionMySuffixDetailComponent>;
        const route = ({ data: of({ statusSession: new StatusSessionMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [StatusSessionMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StatusSessionMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StatusSessionMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.statusSession).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
