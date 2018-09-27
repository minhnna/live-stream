/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LivestreamTestModule } from '../../../test.module';
import { LiveStreamInfoDeleteDialogComponent } from 'app/entities/live-stream-info/live-stream-info-delete-dialog.component';
import { LiveStreamInfoService } from 'app/entities/live-stream-info/live-stream-info.service';

describe('Component Tests', () => {
    describe('LiveStreamInfo Management Delete Component', () => {
        let comp: LiveStreamInfoDeleteDialogComponent;
        let fixture: ComponentFixture<LiveStreamInfoDeleteDialogComponent>;
        let service: LiveStreamInfoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LivestreamTestModule],
                declarations: [LiveStreamInfoDeleteDialogComponent]
            })
                .overrideTemplate(LiveStreamInfoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LiveStreamInfoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LiveStreamInfoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('123');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('123');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
