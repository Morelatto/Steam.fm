package br.com.lp3.rmi.manager.impl;

import br.com.lp3.business.GameJSONParser;
import br.com.lp3.entities.Game;
import br.com.lp3.rmi.manager.GameManager;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class GameManagerImpl implements GameManager {

    @Override
    public List<Game> getGamesByUser(String username) {
        return GameJSONParser.getJogosByUser(username);
    }

}
