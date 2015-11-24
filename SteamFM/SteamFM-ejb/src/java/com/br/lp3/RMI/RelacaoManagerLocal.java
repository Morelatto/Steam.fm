package com.br.lp3.RMI;

import com.br.lp3.entities.GeneroJogo;
import com.br.lp3.entities.Relacao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface RelacaoManagerLocal extends Remote {

    public void create(Relacao relacao) throws RemoteException;

    public List<Relacao> read() throws RemoteException;

    public void update(Relacao relacao) throws RemoteException;

    public void delete(int id) throws RemoteException;

    public List<Relacao> getListaRelacao(List<GeneroJogo> listaGeneroJogo) throws RemoteException;

}
