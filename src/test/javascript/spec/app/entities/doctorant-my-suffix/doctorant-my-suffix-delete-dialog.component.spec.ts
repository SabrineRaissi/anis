/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { DoctorantMySuffixDeleteDialogComponent } from 'app/entities/doctorant-my-suffix/doctorant-my-suffix-delete-dialog.component';
import { DoctorantMySuffixService } from 'app/entities/doctorant-my-suffix/doctorant-my-suffix.service';

describe('Component Tests', () => {
    describe('DoctorantMySuffix Management Delete Component', () => {
        let comp: DoctorantMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<DoctorantMySuffixDeleteDialogComponent>;
        let service: DoctorantMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [DoctorantMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(DoctorantMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DoctorantMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DoctorantMySuffixService);
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
