import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    PossibleValueMySuffixComponent,
    PossibleValueMySuffixDetailComponent,
    PossibleValueMySuffixUpdateComponent,
    PossibleValueMySuffixDeletePopupComponent,
    PossibleValueMySuffixDeleteDialogComponent,
    possibleValueRoute,
    possibleValuePopupRoute
} from './';

const ENTITY_STATES = [...possibleValueRoute, ...possibleValuePopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PossibleValueMySuffixComponent,
        PossibleValueMySuffixDetailComponent,
        PossibleValueMySuffixUpdateComponent,
        PossibleValueMySuffixDeleteDialogComponent,
        PossibleValueMySuffixDeletePopupComponent
    ],
    entryComponents: [
        PossibleValueMySuffixComponent,
        PossibleValueMySuffixUpdateComponent,
        PossibleValueMySuffixDeleteDialogComponent,
        PossibleValueMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestPossibleValueMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
