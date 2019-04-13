/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { EtudeUniversitaireMySuffixDeleteDialogComponent } from 'app/entities/etude-universitaire-my-suffix/etude-universitaire-my-suffix-delete-dialog.component';
import { EtudeUniversitaireMySuffixService } from 'app/entities/etude-universitaire-my-suffix/etude-universitaire-my-suffix.service';

describe('Component Tests', () => {
    describe('EtudeUniversitaireMySuffix Management Delete Component', () => {
        let comp: EtudeUniversitaireMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<EtudeUniversitaireMySuffixDeleteDialogComponent>;
        let service: EtudeUniversitaireMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [EtudeUniversitaireMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(EtudeUniversitaireMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EtudeUniversitaireMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EtudeUniversitaireMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
