export interface ILiveStreamInfo {
    id?: string;
    videoId?: string;
    aiResult?: string;
    result?: string;
}

export class LiveStreamInfo implements ILiveStreamInfo {
    constructor(public id?: string, public videoId?: string, public aiResult?: string, public result?: string) {}
}
