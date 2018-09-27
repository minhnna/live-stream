/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { LivestreamTestModule } from '../../../test.module';
import { LiveStreamInfoUpdateComponent } from 'app/entities/live-stream-info/live-stream-info-update.component';
import { LiveStreamInfoService } from 'app/entities/live-stream-info/live-stream-info.service';
import { LiveStreamInfo } from 'app/shared/model/live-stream-info.model';

describe('Component Tests', () => {
    describe('LiveStreamInfo Management Update Component', () => {
        let comp: LiveStreamInfoUpdateComponent;
        let fixture: ComponentFixture<LiveStreamInfoUpdateComponent>;
        let service: LiveStreamInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LivestreamTestModule],
                declarations: [LiveStreamInfoUpdateComponent]
            })
                .overrideTemplate(LiveStreamInfoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LiveStreamInfoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LiveStreamInfoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new LiveStreamInfo('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.liveStreamInfo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new LiveStreamInfo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.liveStreamInfo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
