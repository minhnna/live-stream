import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LiveStreamInfo } from 'app/shared/model/live-stream-info.model';
import { LiveStreamInfoService } from './live-stream-info.service';
import { LiveStreamInfoComponent } from './live-stream-info.component';
import { LiveStreamInfoDetailComponent } from './live-stream-info-detail.component';
import { LiveStreamInfoUpdateComponent } from './live-stream-info-update.component';
import { LiveStreamInfoDeletePopupComponent } from './live-stream-info-delete-dialog.component';
import { ILiveStreamInfo } from 'app/shared/model/live-stream-info.model';

@Injectable({ providedIn: 'root' })
export class LiveStreamInfoResolve implements Resolve<ILiveStreamInfo> {
    constructor(private service: LiveStreamInfoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((liveStreamInfo: HttpResponse<LiveStreamInfo>) => liveStreamInfo.body));
        }
        return of(new LiveStreamInfo());
    }
}

export const liveStreamInfoRoute: Routes = [
    {
        path: 'live-stream-info',
        component: LiveStreamInfoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'LiveStreamInfos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'live-stream-info/:id/view',
        component: LiveStreamInfoDetailComponent,
        resolve: {
            liveStreamInfo: LiveStreamInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LiveStreamInfos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'live-stream-info/new',
        component: LiveStreamInfoUpdateComponent,
        resolve: {
            liveStreamInfo: LiveStreamInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LiveStreamInfos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'live-stream-info/:id/edit',
        component: LiveStreamInfoUpdateComponent,
        resolve: {
            liveStreamInfo: LiveStreamInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LiveStreamInfos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const liveStreamInfoPopupRoute: Routes = [
    {
        path: 'live-stream-info/:id/delete',
        component: LiveStreamInfoDeletePopupComponent,
        resolve: {
            liveStreamInfo: LiveStreamInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LiveStreamInfos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
