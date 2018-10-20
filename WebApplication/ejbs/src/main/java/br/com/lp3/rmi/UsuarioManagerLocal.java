package br.com.lp3.rmi;

import br.com.lp3.entities.Usuario;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface UsuarioManagerLocal extends Remote {

    void create(Usuario usuario);

    List<Usuario> read();

    void update(Usuario usuario);

    void delete(int id);

}
