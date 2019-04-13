import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DeviceMetadataMySuffix } from 'app/shared/model/device-metadata-my-suffix.model';
import { DeviceMetadataMySuffixService } from './device-metadata-my-suffix.service';
import { DeviceMetadataMySuffixComponent } from './device-metadata-my-suffix.component';
import { DeviceMetadataMySuffixDetailComponent } from './device-metadata-my-suffix-detail.component';
import { DeviceMetadataMySuffixUpdateComponent } from './device-metadata-my-suffix-update.component';
import { DeviceMetadataMySuffixDeletePopupComponent } from './device-metadata-my-suffix-delete-dialog.component';
import { IDeviceMetadataMySuffix } from 'app/shared/model/device-metadata-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class DeviceMetadataMySuffixResolve implements Resolve<IDeviceMetadataMySuffix> {
    constructor(private service: DeviceMetadataMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDeviceMetadataMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DeviceMetadataMySuffix>) => response.ok),
                map((deviceMetadata: HttpResponse<DeviceMetadataMySuffix>) => deviceMetadata.body)
            );
        }
        return of(new DeviceMetadataMySuffix());
    }
}

export const deviceMetadataRoute: Routes = [
    {
        path: '',
        component: DeviceMetadataMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterrtestApp.deviceMetadata.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DeviceMetadataMySuffixDetailComponent,
        resolve: {
            deviceMetadata: DeviceMetadataMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.deviceMetadata.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DeviceMetadataMySuffixUpdateComponent,
        resolve: {
            deviceMetadata: DeviceMetadataMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.deviceMetadata.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DeviceMetadataMySuffixUpdateComponent,
        resolve: {
            deviceMetadata: DeviceMetadataMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.deviceMetadata.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const deviceMetadataPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DeviceMetadataMySuffixDeletePopupComponent,
        resolve: {
            deviceMetadata: DeviceMetadataMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterrtestApp.deviceMetadata.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
