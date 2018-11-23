package br.com.lp3.rmi.manager.impl;

import br.com.lp3.business.GameJSONParser;
import br.com.lp3.entities.dto.Game;
import br.com.lp3.rmi.manager.GameManager;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class GameManagerImpl implements GameManager {

    @Override
    public List<Game> getGamesBySteamId(String steamId) {
        return GameJSONParser.getGamesBySteamId(steamId);
    }

}
