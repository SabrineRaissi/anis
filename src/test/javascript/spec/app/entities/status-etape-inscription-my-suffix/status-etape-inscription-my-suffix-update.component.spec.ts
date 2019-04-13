/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { StatusEtapeInscriptionMySuffixUpdateComponent } from 'app/entities/status-etape-inscription-my-suffix/status-etape-inscription-my-suffix-update.component';
import { StatusEtapeInscriptionMySuffixService } from 'app/entities/status-etape-inscription-my-suffix/status-etape-inscription-my-suffix.service';
import { StatusEtapeInscriptionMySuffix } from 'app/shared/model/status-etape-inscription-my-suffix.model';

describe('Component Tests', () => {
    describe('StatusEtapeInscriptionMySuffix Management Update Component', () => {
        let comp: StatusEtapeInscriptionMySuffixUpdateComponent;
        let fixture: ComponentFixture<StatusEtapeInscriptionMySuffixUpdateComponent>;
        let service: StatusEtapeInscriptionMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [StatusEtapeInscriptionMySuffixUpdateComponent]
            })
                .overrideTemplate(StatusEtapeInscriptionMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StatusEtapeInscriptionMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StatusEtapeInscriptionMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new StatusEtapeInscriptionMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.statusEtapeInscription = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new StatusEtapeInscriptionMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.statusEtapeInscription = entity;
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
