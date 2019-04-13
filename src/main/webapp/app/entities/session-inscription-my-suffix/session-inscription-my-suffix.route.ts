import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SessionInscriptionMySuffix } from 'app/shared/model/session-inscription-my-suffix.model';
import { SessionInscriptionMySuffixService } from './session-inscription-my-suffix.service';
import { SessionInscriptionMySuffixComponent } from './session-inscription-my-suffix.component';
import { SessionInscriptionMySuffixDetailComponent } from './session-inscription-my-suffix-detail.component';
import { SessionInscriptionMySuffixUpdateComponent } from './session-inscription-my-suffix-update.component';
import { SessionInscriptionMySuffixDeletePopupComponent } from './session-inscription-my-suffix-delete-dialog.component';
import { ISessionInscriptionMySuffix } from 'app/shared/model/session-inscription-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class SessionInscriptionMySuffixResolve implements Resolve<ISessionInscriptionMySuffix> {
    constructor(private service: SessionInscriptionMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISessionInscriptionMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SessionInscriptionMySuffix>) => response.ok),
                map((sessionInscription: HttpResponse<SessionInscriptionMySuffix>) => sessionInscription.body)
            );
        }
        return of(new SessionInscriptionMySuffix());
    }
}

export const sessionInscriptionRoute: Routes = [
    {
        path: '',
        component: SessionInscriptionMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.sessionInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SessionInscriptionMySuffixDetailComponent,
        resolve: {
            sessionInscription: SessionInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.sessionInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SessionInscriptionMySuffixUpdateComponent,
        resolve: {
            sessionInscription: SessionInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.sessionInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SessionInscriptionMySuffixUpdateComponent,
        resolve: {
            sessionInscription: SessionInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.sessionInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sessionInscriptionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SessionInscriptionMySuffixDeletePopupComponent,
        resolve: {
            sessionInscription: SessionInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.sessionInscription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
