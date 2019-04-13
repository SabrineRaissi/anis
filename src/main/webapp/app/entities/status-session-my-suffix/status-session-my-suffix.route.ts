import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StatusSessionMySuffix } from 'app/shared/model/status-session-my-suffix.model';
import { StatusSessionMySuffixService } from './status-session-my-suffix.service';
import { StatusSessionMySuffixComponent } from './status-session-my-suffix.component';
import { StatusSessionMySuffixDetailComponent } from './status-session-my-suffix-detail.component';
import { StatusSessionMySuffixUpdateComponent } from './status-session-my-suffix-update.component';
import { StatusSessionMySuffixDeletePopupComponent } from './status-session-my-suffix-delete-dialog.component';
import { IStatusSessionMySuffix } from 'app/shared/model/status-session-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class StatusSessionMySuffixResolve implements Resolve<IStatusSessionMySuffix> {
    constructor(private service: StatusSessionMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IStatusSessionMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<StatusSessionMySuffix>) => response.ok),
                map((statusSession: HttpResponse<StatusSessionMySuffix>) => statusSession.body)
            );
        }
        return of(new StatusSessionMySuffix());
    }
}

export const statusSessionRoute: Routes = [
    {
        path: '',
        component: StatusSessionMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.statusSession.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: StatusSessionMySuffixDetailComponent,
        resolve: {
            statusSession: StatusSessionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusSession.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: StatusSessionMySuffixUpdateComponent,
        resolve: {
            statusSession: StatusSessionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusSession.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: StatusSessionMySuffixUpdateComponent,
        resolve: {
            statusSession: StatusSessionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusSession.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const statusSessionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: StatusSessionMySuffixDeletePopupComponent,
        resolve: {
            statusSession: StatusSessionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.statusSession.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
