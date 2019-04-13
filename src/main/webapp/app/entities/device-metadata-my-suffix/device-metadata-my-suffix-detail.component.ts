import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeviceMetadataMySuffix } from 'app/shared/model/device-metadata-my-suffix.model';

@Component({
    selector: 'jhi-device-metadata-my-suffix-detail',
    templateUrl: './device-metadata-my-suffix-detail.component.html'
})
export class DeviceMetadataMySuffixDetailComponent implements OnInit {
    deviceMetadata: IDeviceMetadataMySuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deviceMetadata }) => {
            this.deviceMetadata = deviceMetadata;
        });
    }

    previousState() {
        window.history.back();
    }
}
