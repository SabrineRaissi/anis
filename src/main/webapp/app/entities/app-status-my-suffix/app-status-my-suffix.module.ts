import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    AppStatusMySuffixComponent,
    AppStatusMySuffixDetailComponent,
    AppStatusMySuffixUpdateComponent,
    AppStatusMySuffixDeletePopupComponent,
    AppStatusMySuffixDeleteDialogComponent,
    appStatusRoute,
    appStatusPopupRoute
} from './';

const ENTITY_STATES = [...appStatusRoute, ...appStatusPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AppStatusMySuffixComponent,
        AppStatusMySuffixDetailComponent,
        AppStatusMySuffixUpdateComponent,
        AppStatusMySuffixDeleteDialogComponent,
        AppStatusMySuffixDeletePopupComponent
    ],
    entryComponents: [
        AppStatusMySuffixComponent,
        AppStatusMySuffixUpdateComponent,
        AppStatusMySuffixDeleteDialogComponent,
        AppStatusMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestAppStatusMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
