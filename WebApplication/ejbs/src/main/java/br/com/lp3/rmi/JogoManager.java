package br.com.lp3.rmi;

import br.com.lp3.business.JogoJSONParser;
import br.com.lp3.entities.Game;

import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Stateless
public class JogoManager implements JogoManagerLocal {

    @Override
    public List<Game> getJogosByUser(String username) {
        return JogoJSONParser.getJogosByUser(username);
    }

}
