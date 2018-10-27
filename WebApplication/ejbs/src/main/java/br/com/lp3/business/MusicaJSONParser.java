package br.com.lp3.business;

import br.com.lp3.entities.Music;
import br.com.lp3.utilities.URLGetter;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class MusicaJSONParser {

    private static final String url = "http://ws.audioscrobbler.com/2.0/";
    private static final String apikey = "57ee3318536b23ee81d6b27e36997cde";

    public static List<Music> getMusicaRecomendacao(List<Music> music) {
        List<Music> listaMusicasRecomendadas = new ArrayList<>();

        for (Music music : music) {
            JsonObject mainObj = URLGetter.getContent(url + "?method=track.getSimilar&mbid=" + music.getLastFmId().trim() + "&api_key=" + apikey + "&format=json");
            JsonObject similartracks = mainObj.getJsonObject("similartracks");
            JsonArray tracks = similartracks.getJsonArray("track");

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
                    recMusic.setImage(recTrack.containsKey("image") ? (recTrack.getJsonArray("image").size() == 6 ? recTrack.getJsonArray("image").getJsonObject(4).getString("#text", "") : recTrack.getJsonArray("image").getJsonObject(3).getString("#text", "")) : "");
                    recMusic.setDescription(recTrack.containsValue("wiki") ? recTrack.getJsonObject("wiki").getString("summary", "") : "");
                    listaMusicasRecomendadas.add(recMusic);
                }
            }
        }
        return listaMusicasRecomendadas;
    }

}
