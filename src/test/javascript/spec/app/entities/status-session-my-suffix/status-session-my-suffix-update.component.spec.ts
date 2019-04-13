/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { StatusSessionMySuffixUpdateComponent } from 'app/entities/status-session-my-suffix/status-session-my-suffix-update.component';
import { StatusSessionMySuffixService } from 'app/entities/status-session-my-suffix/status-session-my-suffix.service';
import { StatusSessionMySuffix } from 'app/shared/model/status-session-my-suffix.model';

describe('Component Tests', () => {
    describe('StatusSessionMySuffix Management Update Component', () => {
        let comp: StatusSessionMySuffixUpdateComponent;
        let fixture: ComponentFixture<StatusSessionMySuffixUpdateComponent>;
        let service: StatusSessionMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [StatusSessionMySuffixUpdateComponent]
            })
                .overrideTemplate(StatusSessionMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StatusSessionMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StatusSessionMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new StatusSessionMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.statusSession = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new StatusSessionMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.statusSession = entity;
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
