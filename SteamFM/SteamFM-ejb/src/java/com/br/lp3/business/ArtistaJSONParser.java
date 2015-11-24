package com.br.lp3.business;

import com.br.lp3.entities.Artista;
import com.br.lp3.utilities.URLGetter;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class ArtistaJSONParser {

    private static final String apikey = "57ee3318536b23ee81d6b27e36997cde";
    private static final String url = "http://ws.audioscrobbler.com/2.0/";

    public static List<Artista> getArtistaRecomendacao(List<Artista> artistas) {
        List<Artista> listaArtistasRecomendados = new ArrayList<>();

        for (Artista artista : artistas) {
            JsonObject mainObj = URLGetter.getContent(url + "?method=artist.getSimilar&mbid=" + artista.getIdArtistaLastfm().trim() + "&api_key=" + apikey + "&format=json");

            JsonObject similarartists = mainObj.getJsonObject("similarartists");
            JsonArray artists = similarartists.getJsonArray("artist");

            for (int i = 0; i < 5; i++) {
                JsonObject recArtist = (JsonObject) artists.get(ThreadLocalRandom.current().nextInt(0, artists.size()));
                String mbid = recArtist.getString("mbid", "");
                String url = recArtist.getString("url", "");
                if ("".equals(mbid)) {
                    i--;
                } else {
                    Artista recArtista = new Artista();
                    recArtista.setIdArtistaLastfm(mbid);
                    recArtista.setIdArtistaLastfm(url);
                    recArtista.setNomeArtista(recArtist.getString("name", ""));
                    recArtista.setImagem(recArtist.containsKey("image") ? (recArtist.getJsonArray("image").size() == 6 ? recArtist.getJsonArray("image").getJsonObject(4).getString("#text", "") : recArtist.getJsonArray("image").getJsonObject(3).getString("#text", "")) : "");
                    recArtista.setDescricao(recArtist.containsValue("wiki") ? recArtist.getJsonObject("wiki").getString("summary", "") : "");
                    listaArtistasRecomendados.add(recArtista);
                }
            }
        }
        return listaArtistasRecomendados;
    }

}
