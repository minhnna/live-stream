import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILiveStreamInfo } from 'app/shared/model/live-stream-info.model';
import { LiveStreamInfoService } from './live-stream-info.service';

@Component({
    selector: 'jhi-live-stream-info-delete-dialog',
    templateUrl: './live-stream-info-delete-dialog.component.html'
})
export class LiveStreamInfoDeleteDialogComponent {
    liveStreamInfo: ILiveStreamInfo;

    constructor(
        private liveStreamInfoService: LiveStreamInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.liveStreamInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'liveStreamInfoListModification',
                content: 'Deleted an liveStreamInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-live-stream-info-delete-popup',
    template: ''
})
export class LiveStreamInfoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ liveStreamInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LiveStreamInfoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.liveStreamInfo = liveStreamInfo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
