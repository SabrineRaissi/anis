import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    EtapeInscriptionMySuffixComponent,
    EtapeInscriptionMySuffixDetailComponent,
    EtapeInscriptionMySuffixUpdateComponent,
    EtapeInscriptionMySuffixDeletePopupComponent,
    EtapeInscriptionMySuffixDeleteDialogComponent,
    etapeInscriptionRoute,
    etapeInscriptionPopupRoute
} from './';

const ENTITY_STATES = [...etapeInscriptionRoute, ...etapeInscriptionPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EtapeInscriptionMySuffixComponent,
        EtapeInscriptionMySuffixDetailComponent,
        EtapeInscriptionMySuffixUpdateComponent,
        EtapeInscriptionMySuffixDeleteDialogComponent,
        EtapeInscriptionMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EtapeInscriptionMySuffixComponent,
        EtapeInscriptionMySuffixUpdateComponent,
        EtapeInscriptionMySuffixDeleteDialogComponent,
        EtapeInscriptionMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestEtapeInscriptionMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
