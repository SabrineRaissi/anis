import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';
import { EtablissementMySuffixService } from './etablissement-my-suffix.service';
import { EtablissementMySuffixComponent } from './etablissement-my-suffix.component';
import { EtablissementMySuffixDetailComponent } from './etablissement-my-suffix-detail.component';
import { EtablissementMySuffixUpdateComponent } from './etablissement-my-suffix-update.component';
import { EtablissementMySuffixDeletePopupComponent } from './etablissement-my-suffix-delete-dialog.component';
import { IEtablissementMySuffix } from 'app/shared/model/etablissement-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EtablissementMySuffixResolve implements Resolve<IEtablissementMySuffix> {
    constructor(private service: EtablissementMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEtablissementMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EtablissementMySuffix>) => response.ok),
                map((etablissement: HttpResponse<EtablissementMySuffix>) => etablissement.body)
            );
        }
        return of(new EtablissementMySuffix());
    }
}

export const etablissementRoute: Routes = [
    {
        path: '',
        component: EtablissementMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.etablissement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EtablissementMySuffixDetailComponent,
        resolve: {
            etablissement: EtablissementMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etablissement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EtablissementMySuffixUpdateComponent,
        resolve: {
            etablissement: EtablissementMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etablissement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EtablissementMySuffixUpdateComponent,
        resolve: {
            etablissement: EtablissementMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etablissement.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const etablissementPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EtablissementMySuffixDeletePopupComponent,
        resolve: {
            etablissement: EtablissementMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etablissement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
