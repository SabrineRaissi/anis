/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { AppStatusMySuffixUpdateComponent } from 'app/entities/app-status-my-suffix/app-status-my-suffix-update.component';
import { AppStatusMySuffixService } from 'app/entities/app-status-my-suffix/app-status-my-suffix.service';
import { AppStatusMySuffix } from 'app/shared/model/app-status-my-suffix.model';

describe('Component Tests', () => {
    describe('AppStatusMySuffix Management Update Component', () => {
        let comp: AppStatusMySuffixUpdateComponent;
        let fixture: ComponentFixture<AppStatusMySuffixUpdateComponent>;
        let service: AppStatusMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [AppStatusMySuffixUpdateComponent]
            })
                .overrideTemplate(AppStatusMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AppStatusMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppStatusMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AppStatusMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.appStatus = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AppStatusMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.appStatus = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
