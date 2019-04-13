/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EtudeUniversitaireMySuffixUpdateComponent } from 'app/entities/etude-universitaire-my-suffix/etude-universitaire-my-suffix-update.component';
import { EtudeUniversitaireMySuffixService } from 'app/entities/etude-universitaire-my-suffix/etude-universitaire-my-suffix.service';
import { EtudeUniversitaireMySuffix } from 'app/shared/model/etude-universitaire-my-suffix.model';

describe('Component Tests', () => {
    describe('EtudeUniversitaireMySuffix Management Update Component', () => {
        let comp: EtudeUniversitaireMySuffixUpdateComponent;
        let fixture: ComponentFixture<EtudeUniversitaireMySuffixUpdateComponent>;
        let service: EtudeUniversitaireMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EtudeUniversitaireMySuffixUpdateComponent]
            })
                .overrideTemplate(EtudeUniversitaireMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EtudeUniversitaireMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EtudeUniversitaireMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new EtudeUniversitaireMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.etudeUniversitaire = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new EtudeUniversitaireMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.etudeUniversitaire = entity;
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
