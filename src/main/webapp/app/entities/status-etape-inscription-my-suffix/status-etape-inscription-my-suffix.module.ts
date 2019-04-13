import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    StatusEtapeInscriptionMySuffixComponent,
    StatusEtapeInscriptionMySuffixDetailComponent,
    StatusEtapeInscriptionMySuffixUpdateComponent,
    StatusEtapeInscriptionMySuffixDeletePopupComponent,
    StatusEtapeInscriptionMySuffixDeleteDialogComponent,
    statusEtapeInscriptionRoute,
    statusEtapeInscriptionPopupRoute
} from './';

const ENTITY_STATES = [...statusEtapeInscriptionRoute, ...statusEtapeInscriptionPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StatusEtapeInscriptionMySuffixComponent,
        StatusEtapeInscriptionMySuffixDetailComponent,
        StatusEtapeInscriptionMySuffixUpdateComponent,
        StatusEtapeInscriptionMySuffixDeleteDialogComponent,
        StatusEtapeInscriptionMySuffixDeletePopupComponent
    ],
    entryComponents: [
        StatusEtapeInscriptionMySuffixComponent,
        StatusEtapeInscriptionMySuffixUpdateComponent,
        StatusEtapeInscriptionMySuffixDeleteDialogComponent,
        StatusEtapeInscriptionMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestStatusEtapeInscriptionMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
