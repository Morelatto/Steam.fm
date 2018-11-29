package br.com.lp3.business;

import br.com.lp3.entities.Artist;
import br.com.lp3.utilities.JsonUtils;
import br.com.lp3.utilities.UrlBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import static br.com.lp3.utilities.JsonUtils.*;

// TODO null assertion on json objects
public class ArtistJSONParser {

    private static final int MAX_RECOMMENDATIONS = 5;

    private ArtistJSONParser() {
    }

    public static List<Artist> getRecommendation(Artist artist) {
        List<Artist> recommendations = Arrays.asList(artist);

        try {
            JSONObject mainObject = JsonUtils.readJsonFromUrl(UrlBuilder.lastFmSimilarArtists(artist.getLastFmId()));
            JSONArray similarArtists = mainObject
                    .getJSONObject(LAST_FM_SIMILAR_ARTISTS_KEY)
                    .getJSONArray(LAST_FM_ARTIST_KEY);

            for (int i = 0; i < similarArtists.length() && i < MAX_RECOMMENDATIONS; i++) {
                JSONObject artistJSONObject = similarArtists.getJSONObject(i);
                String lastFmId = artistJSONObject.optString(LAST_FM_MBID_KEY);
                if (lastFmId != null) {
                    recommendations.add(Artist
                            .builder()
                            .lastFmId(lastFmId)
                            .name(artistJSONObject.getString(LAST_FM_NAME_KEY))
                            .image(JsonUtils.getLastFmImage(artistJSONObject.getJSONArray(LAST_FM_IMAGE_KEY)))
                            .url(artistJSONObject.getString(LAST_FM_URL_KEY))
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
