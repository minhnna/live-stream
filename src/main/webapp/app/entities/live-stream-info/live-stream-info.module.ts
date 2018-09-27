import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LivestreamSharedModule } from 'app/shared';
import {
    LiveStreamInfoComponent,
    LiveStreamInfoDetailComponent,
    LiveStreamInfoUpdateComponent,
    LiveStreamInfoDeletePopupComponent,
    LiveStreamInfoDeleteDialogComponent,
    liveStreamInfoRoute,
    liveStreamInfoPopupRoute
} from './';

const ENTITY_STATES = [...liveStreamInfoRoute, ...liveStreamInfoPopupRoute];

@NgModule({
    imports: [LivestreamSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LiveStreamInfoComponent,
        LiveStreamInfoDetailComponent,
        LiveStreamInfoUpdateComponent,
        LiveStreamInfoDeleteDialogComponent,
        LiveStreamInfoDeletePopupComponent
    ],
    entryComponents: [
        LiveStreamInfoComponent,
        LiveStreamInfoUpdateComponent,
        LiveStreamInfoDeleteDialogComponent,
        LiveStreamInfoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LivestreamLiveStreamInfoModule {}
