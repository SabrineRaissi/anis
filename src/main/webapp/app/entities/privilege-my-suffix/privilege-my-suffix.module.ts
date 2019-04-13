import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    PrivilegeMySuffixComponent,
    PrivilegeMySuffixDetailComponent,
    PrivilegeMySuffixUpdateComponent,
    PrivilegeMySuffixDeletePopupComponent,
    PrivilegeMySuffixDeleteDialogComponent,
    privilegeRoute,
    privilegePopupRoute
} from './';

const ENTITY_STATES = [...privilegeRoute, ...privilegePopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PrivilegeMySuffixComponent,
        PrivilegeMySuffixDetailComponent,
        PrivilegeMySuffixUpdateComponent,
        PrivilegeMySuffixDeleteDialogComponent,
        PrivilegeMySuffixDeletePopupComponent
    ],
    entryComponents: [
        PrivilegeMySuffixComponent,
        PrivilegeMySuffixUpdateComponent,
        PrivilegeMySuffixDeleteDialogComponent,
        PrivilegeMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestPrivilegeMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
