package br.com.lp3.rmi;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.MusicReleaseAndGameMap;

import javax.ejb.Stateless;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Stateless
public class RelacaoManager extends UnicastRemoteObject implements RelacaoManagerLocal {

    Registry registro;
    GenericoDAO_Remoto servico;

    public RelacaoManager() throws RemoteException, NotBoundException {
        registro = LocateRegistry.getRegistry("localhost", 1099);
        servico = (GenericoDAO_Remoto) registro.lookup("RelacaoDAO");
    }

    @Override
    public void create(MusicReleaseAndGameMap musicReleaseAndGameMap) {
        servico.create(musicReleaseAndGameMap);
    }

    @Override
    public List<MusicReleaseAndGameMap> read() {
        return (List<MusicReleaseAndGameMap>) servico.read();
    }

    @Override
    public void update(MusicReleaseAndGameMap musicReleaseAndGameMap) {
        servico.update(musicReleaseAndGameMap);
    }

    @Override
    public void delete(int id) {
        servico.delete(id);
    }

    @Override
    public List<MusicReleaseAndGameMap> getListaRelacao(List<GameGenre> listaGameGenres) {
        List<MusicReleaseAndGameMap> listaMusicReleaseAndGameMap = new ArrayList<>();
        for (MusicReleaseAndGameMap musicReleaseAndGameMap : read()) {
            for (GameGenre gameGenre : listaGameGenres) {
                if (Objects.equals(gameGenre.getId(), musicReleaseAndGameMap.getGameGenreId().getId())) {
                    listaMusicReleaseAndGameMap.add(musicReleaseAndGameMap);
                    break;
                }
            }
        }
        return listaMusicReleaseAndGameMap;
    }
}
