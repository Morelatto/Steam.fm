package br.com.lp3.business;

import br.com.lp3.entities.Song;
import br.com.lp3.utilities.JsonUtils;
import br.com.lp3.utilities.UrlBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import static br.com.lp3.utilities.JsonUtils.*;

// TODO null assertion on json objects
public class SongJSONParser {

    private static final int MAX_RECOMMENDATIONS = 5;

    private SongJSONParser() {
    }

    public static List<Song> getRecommendation(Song song) {
        List<Song> recommendations = Arrays.asList(song);

        try {
            JSONObject mainObject = JsonUtils.readJsonFromUrl(UrlBuilder.lastFmSimilarSongs(song.getLastFmId()));
            JSONArray similarTracks = mainObject
                    .getJSONObject(LAST_FM_SIMILAR_TRACKS_KEY)
                    .getJSONArray(LAST_FM_TRACK_KEY);

            for (int i = 0; i < similarTracks.length() && i < MAX_RECOMMENDATIONS; i++) {
                JSONObject trackJSONObject = similarTracks.getJSONObject(i);
                String lastFmId = trackJSONObject.optString(LAST_FM_MBID_KEY);
                if (lastFmId != null) {
                    recommendations.add(Song
                            .builder()
                            .lastFmId(lastFmId)
                            .name(trackJSONObject.getString(LAST_FM_NAME_KEY))
                            .image(JsonUtils.getLastFmImage(trackJSONObject.getJSONArray(LAST_FM_IMAGE_KEY)))
                            .url(trackJSONObject.getString(LAST_FM_URL_KEY))
                            .build());
                }
            }
        } catch (IOException e) {
            // TODO log
            e.printStackTrace();
        }
        return recommendations;
    }

}
