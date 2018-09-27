import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILiveStreamInfo } from 'app/shared/model/live-stream-info.model';

@Component({
    selector: 'jhi-live-stream-info-detail',
    templateUrl: './live-stream-info-detail.component.html'
})
export class LiveStreamInfoDetailComponent implements OnInit {
    liveStreamInfo: ILiveStreamInfo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ liveStreamInfo }) => {
            this.liveStreamInfo = liveStreamInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}
