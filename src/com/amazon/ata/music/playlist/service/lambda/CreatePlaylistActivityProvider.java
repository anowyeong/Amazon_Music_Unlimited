package com.amazon.ata.music.playlist.service.lambda;

import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.music.playlist.service.dependency.ServiceComponent;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import javax.inject.Inject;
import javax.inject.Singleton;

public class CreatePlaylistActivityProvider implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResult> {

//    private static App app;

    ServiceComponent dagger = DaggerServiceComponent.create();

    public CreatePlaylistActivityProvider() {

    }

    @Override
    public CreatePlaylistResult handleRequest(final CreatePlaylistRequest createPlaylistRequest, Context context) {
        return dagger.provideCreatePlaylistActivity().handleRequest(createPlaylistRequest, context);
    }

}
