/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { LastThesesMySuffixUpdateComponent } from 'app/entities/last-theses-my-suffix/last-theses-my-suffix-update.component';
import { LastThesesMySuffixService } from 'app/entities/last-theses-my-suffix/last-theses-my-suffix.service';
import { LastThesesMySuffix } from 'app/shared/model/last-theses-my-suffix.model';

describe('Component Tests', () => {
    describe('LastThesesMySuffix Management Update Component', () => {
        let comp: LastThesesMySuffixUpdateComponent;
        let fixture: ComponentFixture<LastThesesMySuffixUpdateComponent>;
        let service: LastThesesMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [LastThesesMySuffixUpdateComponent]
            })
                .overrideTemplate(LastThesesMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LastThesesMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LastThesesMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new LastThesesMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.lastTheses = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new LastThesesMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.lastTheses = entity;
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
