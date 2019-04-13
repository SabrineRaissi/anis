import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    StatusInscriptionMySuffixComponent,
    StatusInscriptionMySuffixDetailComponent,
    StatusInscriptionMySuffixUpdateComponent,
    StatusInscriptionMySuffixDeletePopupComponent,
    StatusInscriptionMySuffixDeleteDialogComponent,
    statusInscriptionRoute,
    statusInscriptionPopupRoute
} from './';

const ENTITY_STATES = [...statusInscriptionRoute, ...statusInscriptionPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StatusInscriptionMySuffixComponent,
        StatusInscriptionMySuffixDetailComponent,
        StatusInscriptionMySuffixUpdateComponent,
        StatusInscriptionMySuffixDeleteDialogComponent,
        StatusInscriptionMySuffixDeletePopupComponent
    ],
    entryComponents: [
        StatusInscriptionMySuffixComponent,
        StatusInscriptionMySuffixUpdateComponent,
        StatusInscriptionMySuffixDeleteDialogComponent,
        StatusInscriptionMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestStatusInscriptionMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
