/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { DoctorantMySuffixUpdateComponent } from 'app/entities/doctorant-my-suffix/doctorant-my-suffix-update.component';
import { DoctorantMySuffixService } from 'app/entities/doctorant-my-suffix/doctorant-my-suffix.service';
import { DoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';

describe('Component Tests', () => {
    describe('DoctorantMySuffix Management Update Component', () => {
        let comp: DoctorantMySuffixUpdateComponent;
        let fixture: ComponentFixture<DoctorantMySuffixUpdateComponent>;
        let service: DoctorantMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [DoctorantMySuffixUpdateComponent]
            })
                .overrideTemplate(DoctorantMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DoctorantMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DoctorantMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DoctorantMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.doctorant = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DoctorantMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.doctorant = entity;
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
