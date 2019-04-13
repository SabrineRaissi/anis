import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    DoctorantMySuffixComponent,
    DoctorantMySuffixDetailComponent,
    DoctorantMySuffixUpdateComponent,
    DoctorantMySuffixDeletePopupComponent,
    DoctorantMySuffixDeleteDialogComponent,
    doctorantRoute,
    doctorantPopupRoute
} from './';

const ENTITY_STATES = [...doctorantRoute, ...doctorantPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DoctorantMySuffixComponent,
        DoctorantMySuffixDetailComponent,
        DoctorantMySuffixUpdateComponent,
        DoctorantMySuffixDeleteDialogComponent,
        DoctorantMySuffixDeletePopupComponent
    ],
    entryComponents: [
        DoctorantMySuffixComponent,
        DoctorantMySuffixUpdateComponent,
        DoctorantMySuffixDeleteDialogComponent,
        DoctorantMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestDoctorantMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
