package br.com.lp3.rmi.dao.remote;

import br.com.lp3.entities.Relacao;
import br.com.lp3.rmi.dao.RelacaoDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class RelacaoDAO_Remoto extends UnicastRemoteObject implements GenericoDAO_Remoto<Relacao> {

    private final RelacaoDAO DAO;

    public RelacaoDAO_Remoto() throws RemoteException {
        DAO = new RelacaoDAO();
    }

    @Override
    public void create(Relacao relacao) {
        DAO.create(relacao);
    }

    @Override
    public List<Relacao> read() {
        return DAO.read();
    }

    @Override
    public void update(Relacao relacao) {
        DAO.update(relacao);
    }

    @Override
    public void delete(int id) {
        DAO.delete(id);
    }

}
