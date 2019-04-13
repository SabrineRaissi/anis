import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LastThesesMySuffix } from 'app/shared/model/last-theses-my-suffix.model';
import { LastThesesMySuffixService } from './last-theses-my-suffix.service';
import { LastThesesMySuffixComponent } from './last-theses-my-suffix.component';
import { LastThesesMySuffixDetailComponent } from './last-theses-my-suffix-detail.component';
import { LastThesesMySuffixUpdateComponent } from './last-theses-my-suffix-update.component';
import { LastThesesMySuffixDeletePopupComponent } from './last-theses-my-suffix-delete-dialog.component';
import { ILastThesesMySuffix } from 'app/shared/model/last-theses-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class LastThesesMySuffixResolve implements Resolve<ILastThesesMySuffix> {
    constructor(private service: LastThesesMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ILastThesesMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LastThesesMySuffix>) => response.ok),
                map((lastTheses: HttpResponse<LastThesesMySuffix>) => lastTheses.body)
            );
        }
        return of(new LastThesesMySuffix());
    }
}

export const lastThesesRoute: Routes = [
    {
        path: '',
        component: LastThesesMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.lastTheses.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: LastThesesMySuffixDetailComponent,
        resolve: {
            lastTheses: LastThesesMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.lastTheses.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: LastThesesMySuffixUpdateComponent,
        resolve: {
            lastTheses: LastThesesMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.lastTheses.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: LastThesesMySuffixUpdateComponent,
        resolve: {
            lastTheses: LastThesesMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.lastTheses.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const lastThesesPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: LastThesesMySuffixDeletePopupComponent,
        resolve: {
            lastTheses: LastThesesMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.lastTheses.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
