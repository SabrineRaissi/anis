/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EtapeInscriptionMySuffixUpdateComponent } from 'app/entities/etape-inscription-my-suffix/etape-inscription-my-suffix-update.component';
import { EtapeInscriptionMySuffixService } from 'app/entities/etape-inscription-my-suffix/etape-inscription-my-suffix.service';
import { EtapeInscriptionMySuffix } from 'app/shared/model/etape-inscription-my-suffix.model';

describe('Component Tests', () => {
    describe('EtapeInscriptionMySuffix Management Update Component', () => {
        let comp: EtapeInscriptionMySuffixUpdateComponent;
        let fixture: ComponentFixture<EtapeInscriptionMySuffixUpdateComponent>;
        let service: EtapeInscriptionMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EtapeInscriptionMySuffixUpdateComponent]
            })
                .overrideTemplate(EtapeInscriptionMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EtapeInscriptionMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EtapeInscriptionMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new EtapeInscriptionMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.etapeInscription = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new EtapeInscriptionMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.etapeInscription = entity;
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
