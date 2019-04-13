import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';
import { EligibilityMySuffixService } from './eligibility-my-suffix.service';
import { EligibilityMySuffixComponent } from './eligibility-my-suffix.component';
import { EligibilityMySuffixDetailComponent } from './eligibility-my-suffix-detail.component';
import { EligibilityMySuffixUpdateComponent } from './eligibility-my-suffix-update.component';
import { EligibilityMySuffixDeletePopupComponent } from './eligibility-my-suffix-delete-dialog.component';
import { IEligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EligibilityMySuffixResolve implements Resolve<IEligibilityMySuffix> {
    constructor(private service: EligibilityMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEligibilityMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EligibilityMySuffix>) => response.ok),
                map((eligibility: HttpResponse<EligibilityMySuffix>) => eligibility.body)
            );
        }
        return of(new EligibilityMySuffix());
    }
}

export const eligibilityRoute: Routes = [
    {
        path: '',
        component: EligibilityMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.eligibility.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EligibilityMySuffixDetailComponent,
        resolve: {
            eligibility: EligibilityMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.eligibility.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EligibilityMySuffixUpdateComponent,
        resolve: {
            eligibility: EligibilityMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.eligibility.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EligibilityMySuffixUpdateComponent,
        resolve: {
            eligibility: EligibilityMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.eligibility.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const eligibilityPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EligibilityMySuffixDeletePopupComponent,
        resolve: {
            eligibility: EligibilityMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.eligibility.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
