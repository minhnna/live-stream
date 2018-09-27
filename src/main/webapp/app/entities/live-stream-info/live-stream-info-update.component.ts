import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ILiveStreamInfo } from 'app/shared/model/live-stream-info.model';
import { LiveStreamInfoService } from './live-stream-info.service';

@Component({
    selector: 'jhi-live-stream-info-update',
    templateUrl: './live-stream-info-update.component.html'
})
export class LiveStreamInfoUpdateComponent implements OnInit {
    private _liveStreamInfo: ILiveStreamInfo;
    isSaving: boolean;

    constructor(private liveStreamInfoService: LiveStreamInfoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ liveStreamInfo }) => {
            this.liveStreamInfo = liveStreamInfo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.liveStreamInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.liveStreamInfoService.update(this.liveStreamInfo));
        } else {
            this.subscribeToSaveResponse(this.liveStreamInfoService.create(this.liveStreamInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILiveStreamInfo>>) {
        result.subscribe((res: HttpResponse<ILiveStreamInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get liveStreamInfo() {
        return this._liveStreamInfo;
    }

    set liveStreamInfo(liveStreamInfo: ILiveStreamInfo) {
        this._liveStreamInfo = liveStreamInfo;
    }
}
