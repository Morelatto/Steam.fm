package com.br.lp3.RMI;

import com.br.lp3.entities.GeneroJogo;
import com.br.lp3.entities.Relacao;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;

/**
 *
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
    public void create(Relacao relacao) throws RemoteException {
        servico.create(relacao);
    }

    @Override
    public List<Relacao> read() throws RemoteException {
        return (List<Relacao>) servico.read();
    }

    @Override
    public void update(Relacao relacao) throws RemoteException {
        servico.update(relacao);
    }

    @Override
    public void delete(int id) throws RemoteException {
        servico.delete(id);
    }

    @Override
    public List<Relacao> getListaRelacao(List<GeneroJogo> listaGeneroJogo) throws RemoteException {
        List<Relacao> listaRelacao = new ArrayList<>();
        for (Relacao relacao : read()) {
            for (GeneroJogo generoJogo : listaGeneroJogo) {
                if(Objects.equals(generoJogo.getIdGeneroJogo(), relacao.getIdGeneroJogo().getIdGeneroJogo())){
                    listaRelacao.add(relacao);
                    break;
                }
            }
        }
        return listaRelacao;
    }
}
