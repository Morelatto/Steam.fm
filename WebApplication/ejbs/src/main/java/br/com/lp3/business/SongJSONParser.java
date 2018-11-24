package br.com.lp3.business;

import br.com.lp3.entities.Song;
import br.com.lp3.utilities.JsonUtils;
import br.com.lp3.utilities.UrlBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import static br.com.lp3.utilities.JsonUtils.LAST_FM_MBID_KEY;
import static br.com.lp3.utilities.JsonUtils.LAST_FM_NAME_KEY;
import static br.com.lp3.utilities.JsonUtils.LAST_FM_IMAGE_KEY;
import static br.com.lp3.utilities.JsonUtils.LAST_FM_URL_KEY;
import static br.com.lp3.utilities.JsonUtils.LAST_FM_SIMILAR_TRACKS_JSON_KEY;
import static br.com.lp3.utilities.JsonUtils.LAST_FM_TRACK_KEY;

public class SongJSONParser {

    private static final int MAX_RECOMMENDATIONS = 5;

    private SongJSONParser() {
    }

    public static List<Song> getRecommendation(Song song) {
        List<Song> recommendations = new ArrayList<>();

        try {
            JSONObject mainObject = JsonUtils.readJsonFromUrl(UrlBuilder.lastFmSimilarSongs(song.getLastFmId()));
            JSONArray similarTracks = mainObject
                    .getJSONObject(LAST_FM_SIMILAR_TRACKS_JSON_KEY)
                    .getJSONArray(LAST_FM_TRACK_KEY);

            for (int i = 0; i < similarTracks.length() && i < MAX_RECOMMENDATIONS; i++) {
                JSONObject trackJSONObject = similarTracks.getJSONObject(i);
                recommendations.add(Song
                        .builder()
                        .lastFmId(trackJSONObject.getString(LAST_FM_MBID_KEY))
                        .name(trackJSONObject.getString(LAST_FM_NAME_KEY))
                        .image(JsonUtils.getLastFmImage(trackJSONObject.getJSONArray(LAST_FM_IMAGE_KEY)))
                        .url(trackJSONObject.getString(LAST_FM_URL_KEY))
                        .build());
            }
        } catch (IOException e) {
            // TODO log
            e.printStackTrace();
        }
        return recommendations;
    }

}
