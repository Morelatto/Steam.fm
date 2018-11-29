package br.com.lp3.business;

import br.com.lp3.entities.Album;
import br.com.lp3.utilities.JsonUtils;
import br.com.lp3.utilities.UrlBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import static br.com.lp3.utilities.JsonUtils.*;

// TODO null assertion on json objects
public class AlbumJSONParser {

    private static final int MAX_TAGS = 2;
    private static final int MAX_RECOMMENDATIONS_PER_TAG = 5;

    private AlbumJSONParser() {
    }

    public static List<Album> getRecommendation(Album album) {
        List<Album> recommendations = new ArrayList<>();

        try {
            JSONObject mainObject = JsonUtils.readJsonFromUrl(UrlBuilder.lastFmTopTags(album.getLastFmId()));
            JSONObject topTagsObject = mainObject.getJSONObject(LAST_FM_TOP_TAGS_KEY);
            JSONArray tagArray = topTagsObject.getJSONArray(LAST_FM_TAG_KEY);

            for (int i = 0; i < tagArray.length() && i < MAX_TAGS; i++) {
                JSONObject tagObject = tagArray.getJSONObject(i);
                recommendations.addAll(getAlbumsByTag(tagObject.getString(LAST_FM_NAME_KEY)));
            }
        } catch (IOException e) {
            // TODO log
            e.printStackTrace();
        }

        return recommendations;
    }

    private static List<Album> getAlbumsByTag(String tag) {
        List<Album> albumList = new ArrayList<>();
        try {
            JSONObject mainObject = JsonUtils.readJsonFromUrl(UrlBuilder.lastFmTopAlbumsByTag(tag));
            JSONObject albumsObject = mainObject.getJSONObject(LAST_FM_ALBUMS_KEY);
            JSONArray albumsArray = albumsObject.getJSONArray(LAST_FM_ALBUM_KEY);
            for (int i = 0; i < albumsArray.length() && i < MAX_RECOMMENDATIONS_PER_TAG; i++) {
                JSONObject albumObject = albumsArray.getJSONObject(i);
                String lastFmId = albumObject.getString(LAST_FM_MBID_KEY);
                if (lastFmId != null) {
                    albumList.add(Album
                            .builder()
                            .lastFmId(lastFmId)
                            .name(albumObject.getString(LAST_FM_NAME_KEY))
                            .image(JsonUtils.getLastFmImage(albumObject.getJSONArray(LAST_FM_IMAGE_KEY)))
                            .url(albumObject.getString(LAST_FM_URL_KEY))
                            .build());
                }
            }
        } catch (IOException e) {
            // TODO log
            e.printStackTrace();
        }
        return albumList;
    }

}
