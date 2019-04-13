import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    EtudeUniversitaireMySuffixComponent,
    EtudeUniversitaireMySuffixDetailComponent,
    EtudeUniversitaireMySuffixUpdateComponent,
    EtudeUniversitaireMySuffixDeletePopupComponent,
    EtudeUniversitaireMySuffixDeleteDialogComponent,
    etudeUniversitaireRoute,
    etudeUniversitairePopupRoute
} from './';

const ENTITY_STATES = [...etudeUniversitaireRoute, ...etudeUniversitairePopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EtudeUniversitaireMySuffixComponent,
        EtudeUniversitaireMySuffixDetailComponent,
        EtudeUniversitaireMySuffixUpdateComponent,
        EtudeUniversitaireMySuffixDeleteDialogComponent,
        EtudeUniversitaireMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EtudeUniversitaireMySuffixComponent,
        EtudeUniversitaireMySuffixUpdateComponent,
        EtudeUniversitaireMySuffixDeleteDialogComponent,
        EtudeUniversitaireMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestEtudeUniversitaireMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
