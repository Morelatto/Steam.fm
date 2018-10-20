package br.com.lp3.rmi;

import br.com.lp3.entities.GeneroJogo;
import br.com.lp3.entities.Relacao;

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
    public void create(Relacao relacao) {
        servico.create(relacao);
    }

    @Override
    public List<Relacao> read() {
        return (List<Relacao>) servico.read();
    }

    @Override
    public void update(Relacao relacao) {
        servico.update(relacao);
    }

    @Override
    public void delete(int id) {
        servico.delete(id);
    }

    @Override
    public List<Relacao> getListaRelacao(List<GeneroJogo> listaGeneroJogo) {
        List<Relacao> listaRelacao = new ArrayList<>();
        for (Relacao relacao : read()) {
            for (GeneroJogo generoJogo : listaGeneroJogo) {
                if (Objects.equals(generoJogo.getIdGeneroJogo(), relacao.getIdGeneroJogo().getIdGeneroJogo())) {
                    listaRelacao.add(relacao);
                    break;
                }
            }
        }
        return listaRelacao;
    }
}
