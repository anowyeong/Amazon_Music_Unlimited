package com.amazon.ata.music.playlist.service.lambda;

import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.music.playlist.service.dependency.ServiceComponent;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPlaylistActivityProvider implements RequestHandler<GetPlaylistRequest, GetPlaylistResult> {

//    private static App app;

    ServiceComponent dagger = DaggerServiceComponent.create();

    public GetPlaylistActivityProvider() {

    }

    @Override
    public GetPlaylistResult handleRequest(final GetPlaylistRequest getPlaylistRequest, Context context) {
        return dagger.provideGetPlaylistActivity().handleRequest(getPlaylistRequest, context);
    }
}
