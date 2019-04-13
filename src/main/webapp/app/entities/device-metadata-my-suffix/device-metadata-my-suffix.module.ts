import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    DeviceMetadataMySuffixComponent,
    DeviceMetadataMySuffixDetailComponent,
    DeviceMetadataMySuffixUpdateComponent,
    DeviceMetadataMySuffixDeletePopupComponent,
    DeviceMetadataMySuffixDeleteDialogComponent,
    deviceMetadataRoute,
    deviceMetadataPopupRoute
} from './';

const ENTITY_STATES = [...deviceMetadataRoute, ...deviceMetadataPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DeviceMetadataMySuffixComponent,
        DeviceMetadataMySuffixDetailComponent,
        DeviceMetadataMySuffixUpdateComponent,
        DeviceMetadataMySuffixDeleteDialogComponent,
        DeviceMetadataMySuffixDeletePopupComponent
    ],
    entryComponents: [
        DeviceMetadataMySuffixComponent,
        DeviceMetadataMySuffixUpdateComponent,
        DeviceMetadataMySuffixDeleteDialogComponent,
        DeviceMetadataMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestDeviceMetadataMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
