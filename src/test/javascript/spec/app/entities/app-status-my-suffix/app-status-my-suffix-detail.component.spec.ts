/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { AppStatusMySuffixDetailComponent } from 'app/entities/app-status-my-suffix/app-status-my-suffix-detail.component';
import { AppStatusMySuffix } from 'app/shared/model/app-status-my-suffix.model';

describe('Component Tests', () => {
    describe('AppStatusMySuffix Management Detail Component', () => {
        let comp: AppStatusMySuffixDetailComponent;
        let fixture: ComponentFixture<AppStatusMySuffixDetailComponent>;
        const route = ({ data: of({ appStatus: new AppStatusMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [AppStatusMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AppStatusMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AppStatusMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.appStatus).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
