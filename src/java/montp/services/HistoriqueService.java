package montp.services;

import montp.data.dao.HistoriqueDAO;
import montp.data.model.Historique;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HistoriqueService extends GenericService<Historique, HistoriqueDAO> {
}
