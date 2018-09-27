import { NgModule } from '@angular/core';

import { LivestreamSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [LivestreamSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [LivestreamSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class LivestreamSharedCommonModule {}
