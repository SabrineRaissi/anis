import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { InscriptionMySuffix } from 'app/shared/model/inscription-my-suffix.model';
import { InscriptionMySuffixService } from './inscription-my-suffix.service';
import { InscriptionMySuffixComponent } from './inscription-my-suffix.component';
import { InscriptionMySuffixDetailComponent } from './inscription-my-suffix-detail.component';
import { InscriptionMySuffixUpdateComponent } from './inscription-my-suffix-update.component';
import { InscriptionMySuffixDeletePopupComponent } from './inscription-my-suffix-delete-dialog.component';
import { IInscriptionMySuffix } from 'app/shared/model/inscription-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class InscriptionMySuffixResolve implements Resolve<IInscriptionMySuffix> {
    constructor(private service: InscriptionMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IInscriptionMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<InscriptionMySuffix>) => response.ok),
                map((inscription: HttpResponse<InscriptionMySuffix>) => inscription.body)
            );
        }
        return of(new InscriptionMySuffix());
    }
}

export const inscriptionRoute: Routes = [
    {
        path: '',
        component: InscriptionMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.inscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: InscriptionMySuffixDetailComponent,
        resolve: {
            inscription: InscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.inscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: InscriptionMySuffixUpdateComponent,
        resolve: {
            inscription: InscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.inscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: InscriptionMySuffixUpdateComponent,
        resolve: {
            inscription: InscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.inscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const inscriptionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: InscriptionMySuffixDeletePopupComponent,
        resolve: {
            inscription: InscriptionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.inscription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
