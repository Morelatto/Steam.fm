package br.com.lp3.business;

import br.com.lp3.entities.dto.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameGenreJSONParser {

    public static List<String> getListaGenerosBySteamId(List<Game> gameList) {
        List<String> gameGenreList = new ArrayList<>();
        for (Game game : gameList) {
            for (String gameGenre : getListaGeneros(game.getId())) {
                gameGenreList.add(gameGenre);
            }
        }
        return new ArrayList<>(new LinkedHashSet<>(gameGenreList));
    }

    public static List<String> getListaGenerosByJogo(Long ID) {
        return getListaGeneros(ID);
    }

    private static List<String> getListaGeneros(Long game) {
        List<String> listaGeneroJogos = new ArrayList<>();
        try {
            URL address = new URL("http://store.steampowered.com/app/" + game + "/?l=brazilian");
            BufferedReader in = new BufferedReader(new InputStreamReader(address.openStream(), StandardCharsets.UTF_8));
            // BufferedReader in = FileTest.getPageBR("C:\\Temp\\SteamFM\\SteamFM\\dmc.html");
            String inputLine;
            boolean find = false;
            while ((inputLine = in.readLine()) != null) {
                if (find == true) {
                    find = false;
                    listaGeneroJogos.add(findGenero(inputLine));
                }
                if (inputLine.contains("\"app_tag\"") && inputLine.contains("display: none;")) {
                    find = true;
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(GameGenreJSONParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GameGenreJSONParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameGenreJSONParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaGeneroJogos;
    }

    private static String findGenero(String line) {
        int i;
        String genre = "";
        for (i = 0; line.charAt(i) == '	'; i++) {
        }
        for (int j = i; j < line.length() && line.charAt(j) != '	'; j++) {
            genre += line.charAt(j);
        }
        return genre;
    }

}
