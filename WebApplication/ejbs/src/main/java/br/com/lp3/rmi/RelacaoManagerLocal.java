package br.com.lp3.rmi;

import br.com.lp3.entities.GeneroJogo;
import br.com.lp3.entities.Relacao;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface RelacaoManagerLocal extends Remote {

    void create(Relacao relacao);

    List<Relacao> read();

    void update(Relacao relacao);

    void delete(int id);

    List<Relacao> getListaRelacao(List<GeneroJogo> listaGeneroJogo);

}
