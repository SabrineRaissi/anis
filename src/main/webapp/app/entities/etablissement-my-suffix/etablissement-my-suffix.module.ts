import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    EtablissementMySuffixComponent,
    EtablissementMySuffixDetailComponent,
    EtablissementMySuffixUpdateComponent,
    EtablissementMySuffixDeletePopupComponent,
    EtablissementMySuffixDeleteDialogComponent,
    etablissementRoute,
    etablissementPopupRoute
} from './';

const ENTITY_STATES = [...etablissementRoute, ...etablissementPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EtablissementMySuffixComponent,
        EtablissementMySuffixDetailComponent,
        EtablissementMySuffixUpdateComponent,
        EtablissementMySuffixDeleteDialogComponent,
        EtablissementMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EtablissementMySuffixComponent,
        EtablissementMySuffixUpdateComponent,
        EtablissementMySuffixDeleteDialogComponent,
        EtablissementMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestEtablissementMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
