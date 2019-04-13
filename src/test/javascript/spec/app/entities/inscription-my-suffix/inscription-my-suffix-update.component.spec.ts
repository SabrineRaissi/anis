/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { InscriptionMySuffixUpdateComponent } from 'app/entities/inscription-my-suffix/inscription-my-suffix-update.component';
import { InscriptionMySuffixService } from 'app/entities/inscription-my-suffix/inscription-my-suffix.service';
import { InscriptionMySuffix } from 'app/shared/model/inscription-my-suffix.model';

describe('Component Tests', () => {
    describe('InscriptionMySuffix Management Update Component', () => {
        let comp: InscriptionMySuffixUpdateComponent;
        let fixture: ComponentFixture<InscriptionMySuffixUpdateComponent>;
        let service: InscriptionMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [InscriptionMySuffixUpdateComponent]
            })
                .overrideTemplate(InscriptionMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InscriptionMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InscriptionMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new InscriptionMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.inscription = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new InscriptionMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.inscription = entity;
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
