package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.AlbumTrackDao;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.exceptions.AlbumTrackNotFoundException;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AlbumTrackDaoTest {

    @InjectMocks
    AlbumTrackDao albumTrackDao;

    @Mock
    DynamoDBMapper dynamoDBMapper;

    AlbumTrack expectedAlbumTrack;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        expectedAlbumTrack = new AlbumTrack();
        expectedAlbumTrack.setAlbumName("expectedTrack");
        expectedAlbumTrack.setTrackNumber(20);
        expectedAlbumTrack.setSongTitle("expectedSongTitle");
        expectedAlbumTrack.setAsin("123");
    }

    @Test
    public void getAlbumTrack_withValidParameters_returnsAlbumTrack() {

        // GIVEN
        String givenAsin = "123";
        int givenTrackNumber = 20;

        // WHEN
        when(albumTrackDao.getAlbumTrack(givenAsin, givenTrackNumber)).thenReturn(expectedAlbumTrack);
        AlbumTrack albumTrack = albumTrackDao.getAlbumTrack(givenAsin, givenTrackNumber);
        //THEN
        assertEquals(expectedAlbumTrack, albumTrack);

    }

    @Test
    public void getAlbumTrack_withInvalidAsin_throwsAlbumTrackNotFoundException() {

        // GIVEN
        String givenAsin = "123";
        String wrongAsin = "000";
        int givenTrackNumber = 20;

        // WHEN
        when(dynamoDBMapper.load(AlbumTrack.class,wrongAsin,givenTrackNumber)).thenReturn(null);
        AlbumTrack albumTrack = dynamoDBMapper.load(AlbumTrack.class, wrongAsin, givenTrackNumber);
        //THEN
        assertEquals(null, albumTrack);
        assertThrows(AlbumTrackNotFoundException.class, () -> albumTrackDao.getAlbumTrack(wrongAsin, givenTrackNumber));

    }

    @Test
    public void getAlbumTrack_withInvalidTrackNumber_throwsAlbumTrackNotFoundException() {

        // GIVEN
        String givenAsin = "123";
        int wrongTrackNumber = 56;

        // WHEN
        when(dynamoDBMapper.load(AlbumTrack.class,givenAsin,wrongTrackNumber)).thenReturn(null);
        AlbumTrack albumTrack = dynamoDBMapper.load(AlbumTrack.class, givenAsin, wrongTrackNumber);
        //THEN
        assertEquals(null, albumTrack);
        assertThrows(AlbumTrackNotFoundException.class, () -> albumTrackDao.getAlbumTrack(givenAsin, wrongTrackNumber));

    }


}
