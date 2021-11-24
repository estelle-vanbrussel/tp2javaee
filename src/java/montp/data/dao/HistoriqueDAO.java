package montp.data.dao;

import montp.data.model.Historique;
import montp.data.model.Panier;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HistoriqueDAO extends GenericDAO<Historique> {
    public HistoriqueDAO() {
        super(Historique.class);
    }
}
