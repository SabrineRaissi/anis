import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterrtestSharedModule } from 'app/shared';
import {
    PasswordResetTokenMySuffixComponent,
    PasswordResetTokenMySuffixDetailComponent,
    PasswordResetTokenMySuffixUpdateComponent,
    PasswordResetTokenMySuffixDeletePopupComponent,
    PasswordResetTokenMySuffixDeleteDialogComponent,
    passwordResetTokenRoute,
    passwordResetTokenPopupRoute
} from './';

const ENTITY_STATES = [...passwordResetTokenRoute, ...passwordResetTokenPopupRoute];

@NgModule({
    imports: [JhipsterrtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PasswordResetTokenMySuffixComponent,
        PasswordResetTokenMySuffixDetailComponent,
        PasswordResetTokenMySuffixUpdateComponent,
        PasswordResetTokenMySuffixDeleteDialogComponent,
        PasswordResetTokenMySuffixDeletePopupComponent
    ],
    entryComponents: [
        PasswordResetTokenMySuffixComponent,
        PasswordResetTokenMySuffixUpdateComponent,
        PasswordResetTokenMySuffixDeleteDialogComponent,
        PasswordResetTokenMySuffixDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestPasswordResetTokenMySuffixModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
