/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterrtestTestModule } from '../../../test.module';
import { DeviceMetadataMySuffixDetailComponent } from 'app/entities/device-metadata-my-suffix/device-metadata-my-suffix-detail.component';
import { DeviceMetadataMySuffix } from 'app/shared/model/device-metadata-my-suffix.model';

describe('Component Tests', () => {
    describe('DeviceMetadataMySuffix Management Detail Component', () => {
        let comp: DeviceMetadataMySuffixDetailComponent;
        let fixture: ComponentFixture<DeviceMetadataMySuffixDetailComponent>;
        const route = ({ data: of({ deviceMetadata: new DeviceMetadataMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterrtestTestModule],
                declarations: [DeviceMetadataMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DeviceMetadataMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeviceMetadataMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.deviceMetadata).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
