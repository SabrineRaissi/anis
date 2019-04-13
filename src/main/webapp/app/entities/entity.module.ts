import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'region-my-suffix',
                loadChildren: './region-my-suffix/region-my-suffix.module#JhipsterrtestRegionMySuffixModule'
            },
            {
                path: 'country-my-suffix',
                loadChildren: './country-my-suffix/country-my-suffix.module#JhipsterrtestCountryMySuffixModule'
            },
            {
                path: 'location-my-suffix',
                loadChildren: './location-my-suffix/location-my-suffix.module#JhipsterrtestLocationMySuffixModule'
            },
            {
                path: 'department-my-suffix',
                loadChildren: './department-my-suffix/department-my-suffix.module#JhipsterrtestDepartmentMySuffixModule'
            },
            {
                path: 'task-my-suffix',
                loadChildren: './task-my-suffix/task-my-suffix.module#JhipsterrtestTaskMySuffixModule'
            },
            {
                path: 'employee-my-suffix',
                loadChildren: './employee-my-suffix/employee-my-suffix.module#JhipsterrtestEmployeeMySuffixModule'
            },
            {
                path: 'job-my-suffix',
                loadChildren: './job-my-suffix/job-my-suffix.module#JhipsterrtestJobMySuffixModule'
            },
            {
                path: 'job-history-my-suffix',
                loadChildren: './job-history-my-suffix/job-history-my-suffix.module#JhipsterrtestJobHistoryMySuffixModule'
            },
            {
                path: 'device-metadata-my-suffix',
                loadChildren: './device-metadata-my-suffix/device-metadata-my-suffix.module#JhipsterrtestDeviceMetadataMySuffixModule'
            },
            {
                path: 'possible-value-my-suffix',
                loadChildren: './possible-value-my-suffix/possible-value-my-suffix.module#JhipsterrtestPossibleValueMySuffixModule'
            },
            {
                path: 'last-theses-my-suffix',
                loadChildren: './last-theses-my-suffix/last-theses-my-suffix.module#JhipsterrtestLastThesesMySuffixModule'
            },
            {
                path: 'eligibility-my-suffix',
                loadChildren: './eligibility-my-suffix/eligibility-my-suffix.module#JhipsterrtestEligibilityMySuffixModule'
            },
            {
                path: 'etape-inscription-my-suffix',
                loadChildren: './etape-inscription-my-suffix/etape-inscription-my-suffix.module#JhipsterrtestEtapeInscriptionMySuffixModule'
            },
            {
                path: 'inscription-my-suffix',
                loadChildren: './inscription-my-suffix/inscription-my-suffix.module#JhipsterrtestInscriptionMySuffixModule'
            },
            {
                path: 'doctorant-my-suffix',
                loadChildren: './doctorant-my-suffix/doctorant-my-suffix.module#JhipsterrtestDoctorantMySuffixModule'
            },
            {
                path: 'etablissement-my-suffix',
                loadChildren: './etablissement-my-suffix/etablissement-my-suffix.module#JhipsterrtestEtablissementMySuffixModule'
            },
            {
                path: 'these-my-suffix',
                loadChildren: './these-my-suffix/these-my-suffix.module#JhipsterrtestTheseMySuffixModule'
            },
            {
                path: 'app-status-my-suffix',
                loadChildren: './app-status-my-suffix/app-status-my-suffix.module#JhipsterrtestAppStatusMySuffixModule'
            },
            {
                path: 'status-etape-inscription-my-suffix',
                loadChildren:
                    './status-etape-inscription-my-suffix/status-etape-inscription-my-suffix.module#JhipsterrtestStatusEtapeInscriptionMySuffixModule'
            },
            {
                path: 'etude-universitaire-my-suffix',
                loadChildren:
                    './etude-universitaire-my-suffix/etude-universitaire-my-suffix.module#JhipsterrtestEtudeUniversitaireMySuffixModule'
            },
            {
                path: 'status-inscription-my-suffix',
                loadChildren:
                    './status-inscription-my-suffix/status-inscription-my-suffix.module#JhipsterrtestStatusInscriptionMySuffixModule'
            },
            {
                path: 'session-inscription-my-suffix',
                loadChildren:
                    './session-inscription-my-suffix/session-inscription-my-suffix.module#JhipsterrtestSessionInscriptionMySuffixModule'
            },
            {
                path: 'privilege-my-suffix',
                loadChildren: './privilege-my-suffix/privilege-my-suffix.module#JhipsterrtestPrivilegeMySuffixModule'
            },
            {
                path: 'password-reset-token-my-suffix',
                loadChildren:
                    './password-reset-token-my-suffix/password-reset-token-my-suffix.module#JhipsterrtestPasswordResetTokenMySuffixModule'
            },
            {
                path: 'status-session-my-suffix',
                loadChildren: './status-session-my-suffix/status-session-my-suffix.module#JhipsterrtestStatusSessionMySuffixModule'
            },
            {
                path: 'possible-value-my-suffix',
                loadChildren: './possible-value-my-suffix/possible-value-my-suffix.module#JhipsterrtestPossibleValueMySuffixModule'
            },
            {
                path: 'eligibility-my-suffix',
                loadChildren: './eligibility-my-suffix/eligibility-my-suffix.module#JhipsterrtestEligibilityMySuffixModule'
            },
            {
                path: 'eligibility-my-suffix',
                loadChildren: './eligibility-my-suffix/eligibility-my-suffix.module#JhipsterrtestEligibilityMySuffixModule'
            },
            {
                path: 'these-my-suffix',
                loadChildren: './these-my-suffix/these-my-suffix.module#JhipsterrtestTheseMySuffixModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterrtestEntityModule {}
