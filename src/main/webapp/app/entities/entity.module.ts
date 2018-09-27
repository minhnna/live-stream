import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { LivestreamLiveStreamInfoModule } from './live-stream-info/live-stream-info.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        LivestreamLiveStreamInfoModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LivestreamEntityModule {}
