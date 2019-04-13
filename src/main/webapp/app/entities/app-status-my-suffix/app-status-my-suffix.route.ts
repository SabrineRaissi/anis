import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AppStatusMySuffix } from 'app/shared/model/app-status-my-suffix.model';
import { AppStatusMySuffixService } from './app-status-my-suffix.service';
import { AppStatusMySuffixComponent } from './app-status-my-suffix.component';
import { AppStatusMySuffixDetailComponent } from './app-status-my-suffix-detail.component';
import { AppStatusMySuffixUpdateComponent } from './app-status-my-suffix-update.component';
import { AppStatusMySuffixDeletePopupComponent } from './app-status-my-suffix-delete-dialog.component';
import { IAppStatusMySuffix } from 'app/shared/model/app-status-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class AppStatusMySuffixResolve implements Resolve<IAppStatusMySuffix> {
    constructor(private service: AppStatusMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAppStatusMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AppStatusMySuffix>) => response.ok),
                map((appStatus: HttpResponse<AppStatusMySuffix>) => appStatus.body)
            );
        }
        return of(new AppStatusMySuffix());
    }
}

export const appStatusRoute: Routes = [
    {
        path: '',
        component: AppStatusMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.appStatus.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AppStatusMySuffixDetailComponent,
        resolve: {
            appStatus: AppStatusMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.appStatus.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AppStatusMySuffixUpdateComponent,
        resolve: {
            appStatus: AppStatusMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.appStatus.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AppStatusMySuffixUpdateComponent,
        resolve: {
            appStatus: AppStatusMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.appStatus.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const appStatusPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AppStatusMySuffixDeletePopupComponent,
        resolve: {
            appStatus: AppStatusMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.appStatus.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
