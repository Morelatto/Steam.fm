package com.br.lp3.RMI;

import com.br.lp3.business.GeneroJogoJSONParser;
import com.br.lp3.entities.GeneroJogo;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
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
    public void create(GeneroJogo generoJogo) throws RemoteException {
        servico.create(generoJogo);
    }

    @Override
    public List<GeneroJogo> read() throws RemoteException {
        return (List<GeneroJogo>) servico.read();
    }

    @Override
    public void update(GeneroJogo generoJogo) throws RemoteException {
        servico.update(generoJogo);
    }

    @Override
    public void delete(int id) throws RemoteException {
        servico.delete(id);
    }

    @Override
    public List<GeneroJogo> getListaGenerosByUser(String username) throws RemoteException {
        return getListaGenerosByGeneroName(GeneroJogoJSONParser.getListaGenerosByUser(username));
    }

    @Override
    public List<GeneroJogo> getListaGenerosByGeneroName(List<String> generosNome) throws RemoteException {
        List<GeneroJogo> listaGenerosObjeto = new ArrayList<>();
        for (GeneroJogo generoJogo : read()) {
            if (generosNome.indexOf(generoJogo.getNomeGenero()) != -1) {
                listaGenerosObjeto.add(generoJogo);
            }
        }
        return listaGenerosObjeto;
    }

}
