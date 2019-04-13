import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    EligibilityMySuffixComponent,
    EligibilityMySuffixDetailComponent,
    EligibilityMySuffixUpdateComponent,
    EligibilityMySuffixDeletePopupComponent,
    EligibilityMySuffixDeleteDialogComponent,
    eligibilityRoute,
    eligibilityPopupRoute
} from './';

const ENTITY_STATES = [...eligibilityRoute, ...eligibilityPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EligibilityMySuffixComponent,
        EligibilityMySuffixDetailComponent,
        EligibilityMySuffixUpdateComponent,
        EligibilityMySuffixDeleteDialogComponent,
        EligibilityMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EligibilityMySuffixComponent,
        EligibilityMySuffixUpdateComponent,
        EligibilityMySuffixDeleteDialogComponent,
        EligibilityMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestEligibilityMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
