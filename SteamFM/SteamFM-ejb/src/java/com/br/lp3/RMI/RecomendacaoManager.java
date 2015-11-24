package com.br.lp3.RMI;

import com.br.lp3.business.AlbumJSONParser;
import com.br.lp3.business.ArtistaJSONParser;
import com.br.lp3.business.MusicaJSONParser;
import com.br.lp3.entities.Album;
import com.br.lp3.entities.Artista;
import com.br.lp3.entities.Musica;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Stateless
public class RecomendacaoManager extends UnicastRemoteObject implements RecomendacaoManagerLocal {

    public RecomendacaoManager() throws RemoteException {
    }

    @Override
    public List<Musica> getMusicaRecomendacao(List<Musica> musicas) throws RemoteException {
        return MusicaJSONParser.getMusicaRecomendacao(musicas);
    }

    @Override
    public List<Album> getAlbumRecomendacao(List<Album> albuns) throws RemoteException {
        return AlbumJSONParser.getAlbumRecomendacao(albuns);

    }

    @Override
    public List<Artista> getArtistaRecomendacao(List<Artista> artistas) throws RemoteException {
        return ArtistaJSONParser.getArtistaRecomendacao(artistas);
    }
    
    
}
