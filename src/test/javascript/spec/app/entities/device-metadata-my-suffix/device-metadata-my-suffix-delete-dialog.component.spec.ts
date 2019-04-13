/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterrtestTestModule } from '../../../test.module';
import { DeviceMetadataMySuffixDeleteDialogComponent } from 'app/entities/device-metadata-my-suffix/device-metadata-my-suffix-delete-dialog.component';
import { DeviceMetadataMySuffixService } from 'app/entities/device-metadata-my-suffix/device-metadata-my-suffix.service';

describe('Component Tests', () => {
    describe('DeviceMetadataMySuffix Management Delete Component', () => {
        let comp: DeviceMetadataMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<DeviceMetadataMySuffixDeleteDialogComponent>;
        let service: DeviceMetadataMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [DeviceMetadataMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(DeviceMetadataMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeviceMetadataMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeviceMetadataMySuffixService);
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
