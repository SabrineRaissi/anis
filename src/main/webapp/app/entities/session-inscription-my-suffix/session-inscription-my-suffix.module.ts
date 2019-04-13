import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    SessionInscriptionMySuffixComponent,
    SessionInscriptionMySuffixDetailComponent,
    SessionInscriptionMySuffixUpdateComponent,
    SessionInscriptionMySuffixDeletePopupComponent,
    SessionInscriptionMySuffixDeleteDialogComponent,
    sessionInscriptionRoute,
    sessionInscriptionPopupRoute
} from './';

const ENTITY_STATES = [...sessionInscriptionRoute, ...sessionInscriptionPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SessionInscriptionMySuffixComponent,
        SessionInscriptionMySuffixDetailComponent,
        SessionInscriptionMySuffixUpdateComponent,
        SessionInscriptionMySuffixDeleteDialogComponent,
        SessionInscriptionMySuffixDeletePopupComponent
    ],
    entryComponents: [
        SessionInscriptionMySuffixComponent,
        SessionInscriptionMySuffixUpdateComponent,
        SessionInscriptionMySuffixDeleteDialogComponent,
        SessionInscriptionMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestSessionInscriptionMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
