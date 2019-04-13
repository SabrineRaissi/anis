/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EligibilityMySuffixUpdateComponent } from 'app/entities/eligibility-my-suffix/eligibility-my-suffix-update.component';
import { EligibilityMySuffixService } from 'app/entities/eligibility-my-suffix/eligibility-my-suffix.service';
import { EligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';

describe('Component Tests', () => {
    describe('EligibilityMySuffix Management Update Component', () => {
        let comp: EligibilityMySuffixUpdateComponent;
        let fixture: ComponentFixture<EligibilityMySuffixUpdateComponent>;
        let service: EligibilityMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EligibilityMySuffixUpdateComponent]
            })
                .overrideTemplate(EligibilityMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EligibilityMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EligibilityMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new EligibilityMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.eligibility = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new EligibilityMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.eligibility = entity;
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
