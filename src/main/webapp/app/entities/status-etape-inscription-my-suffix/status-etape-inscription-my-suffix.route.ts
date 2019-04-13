import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StatusEtapeInscriptionMySuffix } from 'app/shared/model/status-etape-inscription-my-suffix.model';
import { StatusEtapeInscriptionMySuffixService } from './status-etape-inscription-my-suffix.service';
import { StatusEtapeInscriptionMySuffixComponent } from './status-etape-inscription-my-suffix.component';
import { StatusEtapeInscriptionMySuffixDetailComponent } from './status-etape-inscription-my-suffix-detail.component';
import { StatusEtapeInscriptionMySuffixUpdateComponent } from './status-etape-inscription-my-suffix-update.component';
import { StatusEtapeInscriptionMySuffixDeletePopupComponent } from './status-etape-inscription-my-suffix-delete-dialog.component';
import { IStatusEtapeInscriptionMySuffix } from 'app/shared/model/status-etape-inscription-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class StatusEtapeInscriptionMySuffixResolve implements Resolve<IStatusEtapeInscriptionMySuffix> {
    constructor(private service: StatusEtapeInscriptionMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IStatusEtapeInscriptionMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<StatusEtapeInscriptionMySuffix>) => response.ok),
                map((statusEtapeInscription: HttpResponse<StatusEtapeInscriptionMySuffix>) => statusEtapeInscription.body)
            );
        }
        return of(new StatusEtapeInscriptionMySuffix());
    }
}

export const statusEtapeInscriptionRoute: Routes = [
    {
        path: '',
        component: StatusEtapeInscriptionMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.statusEtapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: StatusEtapeInscriptionMySuffixDetailComponent,
        resolve: {
            statusEtapeInscription: StatusEtapeInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusEtapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: StatusEtapeInscriptionMySuffixUpdateComponent,
        resolve: {
            statusEtapeInscription: StatusEtapeInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusEtapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: StatusEtapeInscriptionMySuffixUpdateComponent,
        resolve: {
            statusEtapeInscription: StatusEtapeInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusEtapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const statusEtapeInscriptionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: StatusEtapeInscriptionMySuffixDeletePopupComponent,
        resolve: {
            statusEtapeInscription: StatusEtapeInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusEtapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
