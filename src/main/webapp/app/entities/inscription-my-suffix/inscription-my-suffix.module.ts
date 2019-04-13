import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    InscriptionMySuffixComponent,
    InscriptionMySuffixDetailComponent,
    InscriptionMySuffixUpdateComponent,
    InscriptionMySuffixDeletePopupComponent,
    InscriptionMySuffixDeleteDialogComponent,
    inscriptionRoute,
    inscriptionPopupRoute
} from './';

const ENTITY_STATES = [...inscriptionRoute, ...inscriptionPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InscriptionMySuffixComponent,
        InscriptionMySuffixDetailComponent,
        InscriptionMySuffixUpdateComponent,
        InscriptionMySuffixDeleteDialogComponent,
        InscriptionMySuffixDeletePopupComponent
    ],
    entryComponents: [
        InscriptionMySuffixComponent,
        InscriptionMySuffixUpdateComponent,
        InscriptionMySuffixDeleteDialogComponent,
        InscriptionMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestInscriptionMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
