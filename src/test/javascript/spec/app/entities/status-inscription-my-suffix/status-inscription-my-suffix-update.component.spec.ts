/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { StatusInscriptionMySuffixUpdateComponent } from 'app/entities/status-inscription-my-suffix/status-inscription-my-suffix-update.component';
import { StatusInscriptionMySuffixService } from 'app/entities/status-inscription-my-suffix/status-inscription-my-suffix.service';
import { StatusInscriptionMySuffix } from 'app/shared/model/status-inscription-my-suffix.model';

describe('Component Tests', () => {
    describe('StatusInscriptionMySuffix Management Update Component', () => {
        let comp: StatusInscriptionMySuffixUpdateComponent;
        let fixture: ComponentFixture<StatusInscriptionMySuffixUpdateComponent>;
        let service: StatusInscriptionMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [StatusInscriptionMySuffixUpdateComponent]
            })
                .overrideTemplate(StatusInscriptionMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StatusInscriptionMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StatusInscriptionMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new StatusInscriptionMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.statusInscription = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new StatusInscriptionMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.statusInscription = entity;
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
