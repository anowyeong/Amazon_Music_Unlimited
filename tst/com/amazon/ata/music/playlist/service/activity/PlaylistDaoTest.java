package com.amazon.ata.music.playlist.service.activity;


import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.exceptions.PlaylistNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PlaylistDaoTest {

    @InjectMocks
    private PlaylistDao playlistDao;

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getPlaylist_invalidPlaylist_throwsPlaylistNotFoundException() {

        // GIVEN

        // WHEN
        when(dynamoDBMapper.load(Playlist.class, "not a valid key")).thenReturn(null);
        Playlist playlist = dynamoDBMapper.load(Playlist.class, "not a valid key");

        // THEN
        assertEquals(null, playlist);
        assertThrows(PlaylistNotFoundException.class, () -> playlistDao.getPlaylist(null));

    }

    @Test
    public void getPlaylist_validPlaylist_returnsPlaylist() {

        // GIVEN
        Playlist playlist = new Playlist();
        playlist.setId("I01");
        playlist.setName("rock");

        // WHEN
        when(dynamoDBMapper.load(Playlist.class, "list1")).thenReturn(playlist);
        Playlist res = dynamoDBMapper.load(Playlist.class, "list1");

        // THEN
        assertEquals(playlist, res);
    }
}
