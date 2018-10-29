package br.com.lp3.business;

import br.com.lp3.entities.Artist;
import br.com.lp3.utilities.URLGetter;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static br.com.lp3.utilities.SteamFMConstants.LAST_FM_API_KEY;
import static br.com.lp3.utilities.SteamFMConstants.LAST_FM_API_URL;

public class ArtistJSONParser {

    public static List<Artist> getArtistaRecomendacao(List<Artist> artistas) {
        List<Artist> listaArtistasRecomendados = new ArrayList<>();

        for (Artist artist : artistas) {
            JsonObject mainObj = URLGetter.getContent(LAST_FM_API_URL + "?method=artist.getSimilar&mbid="
                    + artist.getLastFmId().trim() + "&api_key=" + LAST_FM_API_KEY + "&format=json");

            JsonObject similarartists = mainObj.getJsonObject("similarartists");
            JsonArray artists = similarartists.getJsonArray("artist");

            for (int i = 0; i < 5; i++) {
                JsonObject recArtist = (JsonObject) artists.get(ThreadLocalRandom.current().nextInt(0, artists.size()));
                String mbid = recArtist.getString("mbid", "");
                String url = recArtist.getString("url", "");
                if ("".equals(mbid)) {
                    i--;
                } else {
                    Artist recArtista = new Artist();
                    recArtista.setLastFmId(mbid);
                    recArtista.setLastFmId(url);
                    recArtista.setName(recArtist.getString("name", ""));
                    recArtista
                            .setImage(recArtist.containsKey("image") ? (recArtist.getJsonArray("image").size() == 6 ? recArtist
                                    .getJsonArray("image")
                                    .getJsonObject(4)
                                    .getString("#text", "")
                                    : recArtist.getJsonArray("image").getJsonObject(3).getString("#text", ""))
                                    : "");
                    recArtista.setDescription(recArtist.containsValue("wiki") ? recArtist
                            .getJsonObject("wiki")
                            .getString("summary", "") : "");
                    listaArtistasRecomendados.add(recArtista);
                }
            }
        }
        return listaArtistasRecomendados;
    }

}
