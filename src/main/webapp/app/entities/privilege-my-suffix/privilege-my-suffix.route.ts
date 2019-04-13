import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PrivilegeMySuffix } from 'app/shared/model/privilege-my-suffix.model';
import { PrivilegeMySuffixService } from './privilege-my-suffix.service';
import { PrivilegeMySuffixComponent } from './privilege-my-suffix.component';
import { PrivilegeMySuffixDetailComponent } from './privilege-my-suffix-detail.component';
import { PrivilegeMySuffixUpdateComponent } from './privilege-my-suffix-update.component';
import { PrivilegeMySuffixDeletePopupComponent } from './privilege-my-suffix-delete-dialog.component';
import { IPrivilegeMySuffix } from 'app/shared/model/privilege-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class PrivilegeMySuffixResolve implements Resolve<IPrivilegeMySuffix> {
    constructor(private service: PrivilegeMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPrivilegeMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PrivilegeMySuffix>) => response.ok),
                map((privilege: HttpResponse<PrivilegeMySuffix>) => privilege.body)
            );
        }
        return of(new PrivilegeMySuffix());
    }
}

export const privilegeRoute: Routes = [
    {
        path: '',
        component: PrivilegeMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.privilege.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PrivilegeMySuffixDetailComponent,
        resolve: {
            privilege: PrivilegeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.privilege.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PrivilegeMySuffixUpdateComponent,
        resolve: {
            privilege: PrivilegeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.privilege.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PrivilegeMySuffixUpdateComponent,
        resolve: {
            privilege: PrivilegeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.privilege.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const privilegePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PrivilegeMySuffixDeletePopupComponent,
        resolve: {
            privilege: PrivilegeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.privilege.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
