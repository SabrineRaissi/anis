import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StatusInscriptionMySuffix } from 'app/shared/model/status-inscription-my-suffix.model';
import { StatusInscriptionMySuffixService } from './status-inscription-my-suffix.service';
import { StatusInscriptionMySuffixComponent } from './status-inscription-my-suffix.component';
import { StatusInscriptionMySuffixDetailComponent } from './status-inscription-my-suffix-detail.component';
import { StatusInscriptionMySuffixUpdateComponent } from './status-inscription-my-suffix-update.component';
import { StatusInscriptionMySuffixDeletePopupComponent } from './status-inscription-my-suffix-delete-dialog.component';
import { IStatusInscriptionMySuffix } from 'app/shared/model/status-inscription-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class StatusInscriptionMySuffixResolve implements Resolve<IStatusInscriptionMySuffix> {
    constructor(private service: StatusInscriptionMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IStatusInscriptionMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<StatusInscriptionMySuffix>) => response.ok),
                map((statusInscription: HttpResponse<StatusInscriptionMySuffix>) => statusInscription.body)
            );
        }
        return of(new StatusInscriptionMySuffix());
    }
}

export const statusInscriptionRoute: Routes = [
    {
        path: '',
        component: StatusInscriptionMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.statusInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: StatusInscriptionMySuffixDetailComponent,
        resolve: {
            statusInscription: StatusInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: StatusInscriptionMySuffixUpdateComponent,
        resolve: {
            statusInscription: StatusInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: StatusInscriptionMySuffixUpdateComponent,
        resolve: {
            statusInscription: StatusInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const statusInscriptionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: StatusInscriptionMySuffixDeletePopupComponent,
        resolve: {
            statusInscription: StatusInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusInscription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
