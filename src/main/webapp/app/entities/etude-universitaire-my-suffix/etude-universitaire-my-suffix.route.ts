import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EtudeUniversitaireMySuffix } from 'app/shared/model/etude-universitaire-my-suffix.model';
import { EtudeUniversitaireMySuffixService } from './etude-universitaire-my-suffix.service';
import { EtudeUniversitaireMySuffixComponent } from './etude-universitaire-my-suffix.component';
import { EtudeUniversitaireMySuffixDetailComponent } from './etude-universitaire-my-suffix-detail.component';
import { EtudeUniversitaireMySuffixUpdateComponent } from './etude-universitaire-my-suffix-update.component';
import { EtudeUniversitaireMySuffixDeletePopupComponent } from './etude-universitaire-my-suffix-delete-dialog.component';
import { IEtudeUniversitaireMySuffix } from 'app/shared/model/etude-universitaire-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EtudeUniversitaireMySuffixResolve implements Resolve<IEtudeUniversitaireMySuffix> {
    constructor(private service: EtudeUniversitaireMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEtudeUniversitaireMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EtudeUniversitaireMySuffix>) => response.ok),
                map((etudeUniversitaire: HttpResponse<EtudeUniversitaireMySuffix>) => etudeUniversitaire.body)
            );
        }
        return of(new EtudeUniversitaireMySuffix());
    }
}

export const etudeUniversitaireRoute: Routes = [
    {
        path: '',
        component: EtudeUniversitaireMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.etudeUniversitaire.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EtudeUniversitaireMySuffixDetailComponent,
        resolve: {
            etudeUniversitaire: EtudeUniversitaireMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etudeUniversitaire.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EtudeUniversitaireMySuffixUpdateComponent,
        resolve: {
            etudeUniversitaire: EtudeUniversitaireMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etudeUniversitaire.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EtudeUniversitaireMySuffixUpdateComponent,
        resolve: {
            etudeUniversitaire: EtudeUniversitaireMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etudeUniversitaire.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const etudeUniversitairePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EtudeUniversitaireMySuffixDeletePopupComponent,
        resolve: {
            etudeUniversitaire: EtudeUniversitaireMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.etudeUniversitaire.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
