import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';
import { DoctorantMySuffixService } from './doctorant-my-suffix.service';
import { DoctorantMySuffixComponent } from './doctorant-my-suffix.component';
import { DoctorantMySuffixDetailComponent } from './doctorant-my-suffix-detail.component';
import { DoctorantMySuffixUpdateComponent } from './doctorant-my-suffix-update.component';
import { DoctorantMySuffixDeletePopupComponent } from './doctorant-my-suffix-delete-dialog.component';
import { IDoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class DoctorantMySuffixResolve implements Resolve<IDoctorantMySuffix> {
    constructor(private service: DoctorantMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDoctorantMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DoctorantMySuffix>) => response.ok),
                map((doctorant: HttpResponse<DoctorantMySuffix>) => doctorant.body)
            );
        }
        return of(new DoctorantMySuffix());
    }
}

export const doctorantRoute: Routes = [
    {
        path: '',
        component: DoctorantMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.doctorant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DoctorantMySuffixDetailComponent,
        resolve: {
            doctorant: DoctorantMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.doctorant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DoctorantMySuffixUpdateComponent,
        resolve: {
            doctorant: DoctorantMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.doctorant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DoctorantMySuffixUpdateComponent,
        resolve: {
            doctorant: DoctorantMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.doctorant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const doctorantPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DoctorantMySuffixDeletePopupComponent,
        resolve: {
            doctorant: DoctorantMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.doctorant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
