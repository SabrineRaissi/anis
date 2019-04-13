import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PasswordResetTokenMySuffix } from 'app/shared/model/password-reset-token-my-suffix.model';
import { PasswordResetTokenMySuffixService } from './password-reset-token-my-suffix.service';
import { PasswordResetTokenMySuffixComponent } from './password-reset-token-my-suffix.component';
import { PasswordResetTokenMySuffixDetailComponent } from './password-reset-token-my-suffix-detail.component';
import { PasswordResetTokenMySuffixUpdateComponent } from './password-reset-token-my-suffix-update.component';
import { PasswordResetTokenMySuffixDeletePopupComponent } from './password-reset-token-my-suffix-delete-dialog.component';
import { IPasswordResetTokenMySuffix } from 'app/shared/model/password-reset-token-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class PasswordResetTokenMySuffixResolve implements Resolve<IPasswordResetTokenMySuffix> {
    constructor(private service: PasswordResetTokenMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPasswordResetTokenMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PasswordResetTokenMySuffix>) => response.ok),
                map((passwordResetToken: HttpResponse<PasswordResetTokenMySuffix>) => passwordResetToken.body)
            );
        }
        return of(new PasswordResetTokenMySuffix());
    }
}

export const passwordResetTokenRoute: Routes = [
    {
        path: '',
        component: PasswordResetTokenMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.passwordResetToken.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PasswordResetTokenMySuffixDetailComponent,
        resolve: {
            passwordResetToken: PasswordResetTokenMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.passwordResetToken.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PasswordResetTokenMySuffixUpdateComponent,
        resolve: {
            passwordResetToken: PasswordResetTokenMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.passwordResetToken.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PasswordResetTokenMySuffixUpdateComponent,
        resolve: {
            passwordResetToken: PasswordResetTokenMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.passwordResetToken.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const passwordResetTokenPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PasswordResetTokenMySuffixDeletePopupComponent,
        resolve: {
            passwordResetToken: PasswordResetTokenMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.passwordResetToken.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
