package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.SongModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {

        List<String> tags = null;

        if (playlist.getTags() != null) {
            tags = new ArrayList<>(playlist.getTags());
        }

        return PlaylistModel.builder()
                .withId(playlist.getId())
                .withCustomerId(playlist.getCustomerId())
                .withName(playlist.getName())
                .withSongCount(playlist.getSongCount())
                // TODO: convert set to list
                .withTags(tags)
                .build();
    }

    public SongModel toSongModel(AlbumTrack albumTrack) {

        return SongModel.builder()
                .withAlbum(albumTrack.getAlbumName())
                .withAsin(albumTrack.getAsin())
                .withTitle(albumTrack.getSongTitle())
                .withTrackNumber(albumTrack.getTrackNumber())
                .build();
    }

    public List<SongModel> toSongModelList(List<AlbumTrack> albumTrackList) {

        List<SongModel> songModelList = new ArrayList<>();

        for (AlbumTrack albumTrack : albumTrackList) {

            SongModel songModel = SongModel.builder()
                    .withAlbum(albumTrack.getAlbumName())
                    .withAsin(albumTrack.getAsin())
                    .withTitle(albumTrack.getSongTitle())
                    .withTrackNumber(albumTrack.getTrackNumber())
                    .build();

            songModelList.add(songModel);
        }
            return  songModelList;
    }

}
