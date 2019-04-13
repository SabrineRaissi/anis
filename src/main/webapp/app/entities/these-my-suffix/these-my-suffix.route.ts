import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TheseMySuffix } from 'app/shared/model/these-my-suffix.model';
import { TheseMySuffixService } from './these-my-suffix.service';
import { TheseMySuffixComponent } from './these-my-suffix.component';
import { TheseMySuffixDetailComponent } from './these-my-suffix-detail.component';
import { TheseMySuffixUpdateComponent } from './these-my-suffix-update.component';
import { TheseMySuffixDeletePopupComponent } from './these-my-suffix-delete-dialog.component';
import { ITheseMySuffix } from 'app/shared/model/these-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class TheseMySuffixResolve implements Resolve<ITheseMySuffix> {
    constructor(private service: TheseMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITheseMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TheseMySuffix>) => response.ok),
                map((these: HttpResponse<TheseMySuffix>) => these.body)
            );
        }
        return of(new TheseMySuffix());
    }
}

export const theseRoute: Routes = [
    {
        path: '',
        component: TheseMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.these.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TheseMySuffixDetailComponent,
        resolve: {
            these: TheseMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.these.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TheseMySuffixUpdateComponent,
        resolve: {
            these: TheseMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.these.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TheseMySuffixUpdateComponent,
        resolve: {
            these: TheseMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.these.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const thesePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TheseMySuffixDeletePopupComponent,
        resolve: {
            these: TheseMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.these.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
