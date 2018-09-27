/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LivestreamTestModule } from '../../../test.module';
import { LiveStreamInfoDetailComponent } from 'app/entities/live-stream-info/live-stream-info-detail.component';
import { LiveStreamInfo } from 'app/shared/model/live-stream-info.model';

describe('Component Tests', () => {
    describe('LiveStreamInfo Management Detail Component', () => {
        let comp: LiveStreamInfoDetailComponent;
        let fixture: ComponentFixture<LiveStreamInfoDetailComponent>;
        const route = ({ data: of({ liveStreamInfo: new LiveStreamInfo('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LivestreamTestModule],
                declarations: [LiveStreamInfoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LiveStreamInfoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LiveStreamInfoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.liveStreamInfo).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
