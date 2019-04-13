import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PossibleValueMySuffix } from 'app/shared/model/possible-value-my-suffix.model';
import { PossibleValueMySuffixService } from './possible-value-my-suffix.service';
import { PossibleValueMySuffixComponent } from './possible-value-my-suffix.component';
import { PossibleValueMySuffixDetailComponent } from './possible-value-my-suffix-detail.component';
import { PossibleValueMySuffixUpdateComponent } from './possible-value-my-suffix-update.component';
import { PossibleValueMySuffixDeletePopupComponent } from './possible-value-my-suffix-delete-dialog.component';
import { IPossibleValueMySuffix } from 'app/shared/model/possible-value-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class PossibleValueMySuffixResolve implements Resolve<IPossibleValueMySuffix> {
    constructor(private service: PossibleValueMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPossibleValueMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PossibleValueMySuffix>) => response.ok),
                map((possibleValue: HttpResponse<PossibleValueMySuffix>) => possibleValue.body)
            );
        }
        return of(new PossibleValueMySuffix());
    }
}

export const possibleValueRoute: Routes = [
    {
        path: '',
        component: PossibleValueMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.possibleValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PossibleValueMySuffixDetailComponent,
        resolve: {
            possibleValue: PossibleValueMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.possibleValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PossibleValueMySuffixUpdateComponent,
        resolve: {
            possibleValue: PossibleValueMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.possibleValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PossibleValueMySuffixUpdateComponent,
        resolve: {
            possibleValue: PossibleValueMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.possibleValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const possibleValuePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PossibleValueMySuffixDeletePopupComponent,
        resolve: {
            possibleValue: PossibleValueMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.possibleValue.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
