package br.com.lp3.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static final String LAST_FM_MBID_KEY = "mbid";
    public static final String LAST_FM_NAME_KEY = "name";
    public static final String LAST_FM_IMAGE_KEY = "image";
    public static final String LAST_FM_URL_KEY = "url";
    public static final String LAST_FM_SIMILAR_TRACKS_KEY = "similartracks";
    public static final String LAST_FM_SIMILAR_ARTISTS_KEY = "similarartists";
    public static final String LAST_FM_TRACK_KEY = "track";
    public static final String LAST_FM_ARTIST_KEY = "artist";
    public static final String LAST_FM_TOP_TAGS_KEY = "toptags";
    public static final String LAST_FM_TAG_KEY = "tag";
    public static final String LAST_FM_ALBUMS_KEY = "albums";
    public static final String LAST_FM_ALBUM_KEY = "album";
    private static final String LAST_FM_IMAGE_URL_KEY = "#text";

    public static final String STEAM_GAME_ID_KEY = "appid";
    public static final String STEAM_GAME_NAME_KEY = "name";
    public static final String STEAM_RESPONSE_KEY = "response";
    public static final String STEAM_GAMES_KEY = "games";
    public static final String STEAM_ID_KEY = "steamid";

    private JsonUtils() {
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String getLastFmImage(JSONArray imageJsonArray) {
        JSONObject megaImage = imageJsonArray.getJSONObject(4);
        if (megaImage != null) {
            return megaImage.getString(LAST_FM_IMAGE_URL_KEY);
        }

        JSONObject extraLargeImage = imageJsonArray.getJSONObject(3);
        if (extraLargeImage != null) {
            return extraLargeImage.getString(LAST_FM_IMAGE_URL_KEY);
        }

        JSONObject largeImage = imageJsonArray.getJSONObject(2);
        if (largeImage != null) {
            return largeImage.getString(LAST_FM_IMAGE_URL_KEY);
        }

        JSONObject mediumImage = imageJsonArray.getJSONObject(1);
        if (mediumImage != null) {
            return mediumImage.getString(LAST_FM_IMAGE_URL_KEY);
        }

        JSONObject smallImage = imageJsonArray.getJSONObject(0);
        if (smallImage != null) {
            return smallImage.getString(LAST_FM_IMAGE_URL_KEY);
        }

        return null;
    }

}