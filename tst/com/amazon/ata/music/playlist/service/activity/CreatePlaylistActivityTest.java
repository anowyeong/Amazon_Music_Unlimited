package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class CreatePlaylistActivityTest {

    @InjectMocks
    private CreatePlaylistActivity createPlaylistActivity;
    @Mock
    private CreatePlaylistRequest createPlaylistRequest;
    @Mock
    private PlaylistDao playlistDao;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        createPlaylistActivity = new CreatePlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_InvalidCharacterForCustomerId_throwsException() {
        // GIVEN

        //WHEN
        when(createPlaylistRequest.getCustomerId()).thenReturn("customer1\'");
//        when(createPlaylistRequest.getCustomerId()).thenReturn("customer1\"");
//        when(createPlaylistRequest.getCustomerId()).thenReturn("customer1\\");


        when(createPlaylistRequest.getName()).thenReturn("playlist");

        // THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(createPlaylistRequest, null));

    }

    @Test
    public void handleRequest_InvalidCharacterForName_throwsException() {
        // GIVEN

        //WHEN
        when(createPlaylistRequest.getCustomerId()).thenReturn("customer1");

//        when(createPlaylistRequest.getName()).thenReturn("playlist\'");
//        when(createPlaylistRequest.getName()).thenReturn("playlist\"");
        when(createPlaylistRequest.getName()).thenReturn("playlist\\");



        // THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(createPlaylistRequest, null));
    }

    @Test
    public void handleRequest_validParameters_returnsCreatedPlaylistResults() {
        // GIVEN

        String expectedId = "expectedId";
        String expectedName = "playlist";
        String expectedCustomerId = "customer1";

        // WHEN
        CreatePlaylistRequest results = CreatePlaylistRequest.builder()
                .withCustomerId(expectedCustomerId)
                        .withName(expectedName)
                                .build();

        // THEN
        assertEquals(expectedCustomerId, results.getCustomerId());
        assertEquals(expectedName, results.getName());
    }


}
