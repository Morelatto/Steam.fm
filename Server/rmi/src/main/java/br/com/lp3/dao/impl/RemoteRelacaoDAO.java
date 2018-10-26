package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.Relacao;
import br.com.lp3.dao.impl.RelacaoDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteRelacaoDAO extends UnicastRemoteObject implements RemoteDAO<Relacao> {

    private final RelacaoDAO RelacaoDAO;

    public RemoteRelacaoDAO() throws RemoteException {
        RelacaoDAO = new RelacaoDAO();
    }

    @Override
    public Optional<Relacao> get(long id) {
        return RelacaoDAO.get(id);
    }

    @Override
    public List<Relacao> getAll() {
        return RelacaoDAO.getAll();
    }

    @Override
    public void save(Relacao relacao) {
        RelacaoDAO.save(relacao);
    }

    @Override
    public void update(Relacao relacao) {
        RelacaoDAO.update(relacao);
    }

    @Override
    public void delete(Relacao relacao) {
        RelacaoDAO.delete(relacao);
    }

}
