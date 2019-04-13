/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EtablissementMySuffixUpdateComponent } from 'app/entities/etablissement-my-suffix/etablissement-my-suffix-update.component';
import { EtablissementMySuffixService } from 'app/entities/etablissement-my-suffix/etablissement-my-suffix.service';
import { EtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';

describe('Component Tests', () => {
    describe('EtablissementMySuffix Management Update Component', () => {
        let comp: EtablissementMySuffixUpdateComponent;
        let fixture: ComponentFixture<EtablissementMySuffixUpdateComponent>;
        let service: EtablissementMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EtablissementMySuffixUpdateComponent]
            })
                .overrideTemplate(EtablissementMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EtablissementMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EtablissementMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new EtablissementMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.etablissement = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new EtablissementMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.etablissement = entity;
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
