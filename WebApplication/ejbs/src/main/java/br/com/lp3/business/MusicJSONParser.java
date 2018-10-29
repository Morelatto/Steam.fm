package br.com.lp3.business;

import br.com.lp3.entities.Music;
import br.com.lp3.utilities.URLGetter;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static br.com.lp3.utilities.SteamFMConstants.LAST_FM_API_KEY;
import static br.com.lp3.utilities.SteamFMConstants.LAST_FM_API_URL;

public class MusicJSONParser {

    public static List<Music> getMusicaRecomendacao(List<Music> musicList) {
        List<Music> recommendationList = new ArrayList<>();

        for (Music music : musicList) {
            JsonObject mainObj = URLGetter.getContent(LAST_FM_API_URL + "?method=track.getSimilar&mbid="
                    + music.getLastFmId().trim() + "&api_key=" + LAST_FM_API_KEY + "&format=json");
            JsonObject similarTracks = mainObj.getJsonObject("similartracks");
            JsonArray tracks = similarTracks.getJsonArray("track");

            for (int i = 0; i < 5; i++) {
                JsonObject recTrack = (JsonObject) tracks.get(ThreadLocalRandom.current().nextInt(0, tracks.size()));
                String mbid = recTrack.getString("mbid", "");
                String url2 = recTrack.getString("url", "");
                if ("".equals(mbid)) {
                    i--;
                } else {
                    Music recMusic = new Music();
                    recMusic.setLastFmId(mbid);
                    recMusic.setUrl(url2);
                    recMusic.setTitle(recTrack.getString("name", ""));
                    recMusic
                            .setImage(recTrack.containsKey("image") ? (recTrack.getJsonArray("image").size() == 6 ? recTrack
                                    .getJsonArray("image")
                                    .getJsonObject(4)
                                    .getString("#text", "")
                                    : recTrack.getJsonArray("image").getJsonObject(3).getString("#text", ""))
                                    : "");
                    recMusic.setDescription(recTrack.containsValue("wiki") ? recTrack
                            .getJsonObject("wiki")
                            .getString("summary", "") : "");
                    recommendationList.add(recMusic);
                }
            }
        }
        return recommendationList;
    }

}
