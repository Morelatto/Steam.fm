package br.com.lp3.ejb.beans;

import br.com.lp3.business.GameJSONParser;
import br.com.lp3.ejb.GameManager;
import br.com.lp3.entities.dto.Game;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class GameManagerBean implements GameManager {

    @Override
    public List<Game> getGamesBySteamId(String steamId) {
        return GameJSONParser.getGamesBySteamId(steamId);
    }

}
