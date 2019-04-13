/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { TheseMySuffixUpdateComponent } from 'app/entities/these-my-suffix/these-my-suffix-update.component';
import { TheseMySuffixService } from 'app/entities/these-my-suffix/these-my-suffix.service';
import { TheseMySuffix } from 'app/shared/model/these-my-suffix.model';

describe('Component Tests', () => {
    describe('TheseMySuffix Management Update Component', () => {
        let comp: TheseMySuffixUpdateComponent;
        let fixture: ComponentFixture<TheseMySuffixUpdateComponent>;
        let service: TheseMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [TheseMySuffixUpdateComponent]
            })
                .overrideTemplate(TheseMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TheseMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TheseMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TheseMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.these = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TheseMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.these = entity;
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
