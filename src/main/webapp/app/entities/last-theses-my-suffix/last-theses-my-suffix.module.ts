import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    LastThesesMySuffixComponent,
    LastThesesMySuffixDetailComponent,
    LastThesesMySuffixUpdateComponent,
    LastThesesMySuffixDeletePopupComponent,
    LastThesesMySuffixDeleteDialogComponent,
    lastThesesRoute,
    lastThesesPopupRoute
} from './';

const ENTITY_STATES = [...lastThesesRoute, ...lastThesesPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LastThesesMySuffixComponent,
        LastThesesMySuffixDetailComponent,
        LastThesesMySuffixUpdateComponent,
        LastThesesMySuffixDeleteDialogComponent,
        LastThesesMySuffixDeletePopupComponent
    ],
    entryComponents: [
        LastThesesMySuffixComponent,
        LastThesesMySuffixUpdateComponent,
        LastThesesMySuffixDeleteDialogComponent,
        LastThesesMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestLastThesesMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
