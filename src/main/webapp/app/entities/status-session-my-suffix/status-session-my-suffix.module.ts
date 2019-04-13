import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    StatusSessionMySuffixComponent,
    StatusSessionMySuffixDetailComponent,
    StatusSessionMySuffixUpdateComponent,
    StatusSessionMySuffixDeletePopupComponent,
    StatusSessionMySuffixDeleteDialogComponent,
    statusSessionRoute,
    statusSessionPopupRoute
} from './';

const ENTITY_STATES = [...statusSessionRoute, ...statusSessionPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StatusSessionMySuffixComponent,
        StatusSessionMySuffixDetailComponent,
        StatusSessionMySuffixUpdateComponent,
        StatusSessionMySuffixDeleteDialogComponent,
        StatusSessionMySuffixDeletePopupComponent
    ],
    entryComponents: [
        StatusSessionMySuffixComponent,
        StatusSessionMySuffixUpdateComponent,
        StatusSessionMySuffixDeleteDialogComponent,
        StatusSessionMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestStatusSessionMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
