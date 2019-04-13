/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { PossibleValueMySuffixUpdateComponent } from 'app/entities/possible-value-my-suffix/possible-value-my-suffix-update.component';
import { PossibleValueMySuffixService } from 'app/entities/possible-value-my-suffix/possible-value-my-suffix.service';
import { PossibleValueMySuffix } from 'app/shared/model/possible-value-my-suffix.model';

describe('Component Tests', () => {
    describe('PossibleValueMySuffix Management Update Component', () => {
        let comp: PossibleValueMySuffixUpdateComponent;
        let fixture: ComponentFixture<PossibleValueMySuffixUpdateComponent>;
        let service: PossibleValueMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [PossibleValueMySuffixUpdateComponent]
            })
                .overrideTemplate(PossibleValueMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PossibleValueMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PossibleValueMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PossibleValueMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.possibleValue = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PossibleValueMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.possibleValue = entity;
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
