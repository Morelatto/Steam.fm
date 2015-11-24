package com.br.lp3.RMI;

import com.br.lp3.entities.Jogo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface JogoManagerLocal {

    public List<Jogo> getJogosByUser(String username);
    
}
