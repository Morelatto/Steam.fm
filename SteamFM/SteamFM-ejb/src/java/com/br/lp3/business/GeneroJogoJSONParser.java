package com.br.lp3.business;

import com.br.lp3.entities.Jogo;
import com.br.lp3.utilities.FileTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class GeneroJogoJSONParser {

    public static List<String> getListaGenerosByUser(String username) {
        List<String> listaGeneroJogos = new ArrayList<>();
        for (Jogo id : JogoJSONParser.getJogosByUser(username)) {
            for (String generoJogo : getListaGeneros(id.getSteamID())) {
                listaGeneroJogos.add(generoJogo);
            }
        }
        return new ArrayList<>(new LinkedHashSet<>(listaGeneroJogos));
    }

    public static List<String> getListaGenerosByJogo(int ID) {
        return getListaGeneros(ID);
    }

    private static List<String> getListaGeneros(int game) {
        List<String> listaGeneroJogos = new ArrayList<>();
        try {
            URL address = new URL("http://store.steampowered.com/app/" + game + "/?l=brazilian");
            BufferedReader in = FileTest.getPageBR("C:\\Temp\\SteamFM\\SteamFM-master\\SteamFM-master\\SteamFM\\dmc.html");
//            BufferedReader in = new BufferedReader(new InputStreamReader(address.openStream(), "UTF-8"));
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
            Logger.getLogger(GeneroJogoJSONParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GeneroJogoJSONParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneroJogoJSONParser.class.getName()).log(Level.SEVERE, null, ex);
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
