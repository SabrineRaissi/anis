import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EtapeInscriptionMySuffix } from 'app/shared/model/etape-inscription-my-suffix.model';
import { EtapeInscriptionMySuffixService } from './etape-inscription-my-suffix.service';
import { EtapeInscriptionMySuffixComponent } from './etape-inscription-my-suffix.component';
import { EtapeInscriptionMySuffixDetailComponent } from './etape-inscription-my-suffix-detail.component';
import { EtapeInscriptionMySuffixUpdateComponent } from './etape-inscription-my-suffix-update.component';
import { EtapeInscriptionMySuffixDeletePopupComponent } from './etape-inscription-my-suffix-delete-dialog.component';
import { IEtapeInscriptionMySuffix } from 'app/shared/model/etape-inscription-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EtapeInscriptionMySuffixResolve implements Resolve<IEtapeInscriptionMySuffix> {
    constructor(private service: EtapeInscriptionMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEtapeInscriptionMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EtapeInscriptionMySuffix>) => response.ok),
                map((etapeInscription: HttpResponse<EtapeInscriptionMySuffix>) => etapeInscription.body)
            );
        }
        return of(new EtapeInscriptionMySuffix());
    }
}

export const etapeInscriptionRoute: Routes = [
    {
        path: '',
        component: EtapeInscriptionMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.etapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EtapeInscriptionMySuffixDetailComponent,
        resolve: {
            etapeInscription: EtapeInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EtapeInscriptionMySuffixUpdateComponent,
        resolve: {
            etapeInscription: EtapeInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EtapeInscriptionMySuffixUpdateComponent,
        resolve: {
            etapeInscription: EtapeInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const etapeInscriptionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EtapeInscriptionMySuffixDeletePopupComponent,
        resolve: {
            etapeInscription: EtapeInscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etapeInscription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
