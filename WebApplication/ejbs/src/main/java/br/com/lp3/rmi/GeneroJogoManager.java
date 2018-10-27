package br.com.lp3.rmi;

import br.com.lp3.business.GeneroJogoJSONParser;
import br.com.lp3.entities.GameGenre;

import javax.ejb.Stateless;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Stateless
public class GeneroJogoManager extends UnicastRemoteObject implements GeneroJogoManagerLocal {

    Registry registro;
    GenericoDAO_Remoto servico;

    public GeneroJogoManager() throws RemoteException, NotBoundException {
        registro = LocateRegistry.getRegistry("localhost", 1099);
        servico = (GenericoDAO_Remoto) registro.lookup("GeneroJogoDAO");
    }

    @Override
    public void create(GameGenre gameGenre) {
        servico.create(gameGenre);
    }

    @Override
    public List<GameGenre> read() {
        return (List<GameGenre>) servico.read();
    }

    @Override
    public void update(GameGenre gameGenre) {
        servico.update(gameGenre);
    }

    @Override
    public void delete(int id) {
        servico.delete(id);
    }

    @Override
    public List<GameGenre> getListaGenerosByUser(String username) {
        return getListaGenerosByGeneroName(GeneroJogoJSONParser.getListaGenerosByUser(username));
    }

    @Override
    public List<GameGenre> getListaGenerosByGeneroName(List<String> generosNome) {
        List<GameGenre> listaGenerosObjeto = new ArrayList<>();
        for (GameGenre gameGenre : read()) {
            if (generosNome.indexOf(gameGenre.getName()) != -1) {
                listaGenerosObjeto.add(gameGenre);
            }
        }
        return listaGenerosObjeto;
    }

}
