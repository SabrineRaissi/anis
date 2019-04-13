/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { PrivilegeMySuffixUpdateComponent } from 'app/entities/privilege-my-suffix/privilege-my-suffix-update.component';
import { PrivilegeMySuffixService } from 'app/entities/privilege-my-suffix/privilege-my-suffix.service';
import { PrivilegeMySuffix } from 'app/shared/model/privilege-my-suffix.model';

describe('Component Tests', () => {
    describe('PrivilegeMySuffix Management Update Component', () => {
        let comp: PrivilegeMySuffixUpdateComponent;
        let fixture: ComponentFixture<PrivilegeMySuffixUpdateComponent>;
        let service: PrivilegeMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [PrivilegeMySuffixUpdateComponent]
            })
                .overrideTemplate(PrivilegeMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PrivilegeMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PrivilegeMySuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PrivilegeMySuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.privilege = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PrivilegeMySuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.privilege = entity;
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
