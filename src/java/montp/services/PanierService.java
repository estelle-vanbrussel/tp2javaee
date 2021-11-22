package montp.services;

import montp.data.dao.PanierDAO;
import montp.data.model.Company;
import montp.data.model.Panier;
import montp.data.model.security.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PanierService extends GenericService<Panier, PanierDAO> {
    public List<Panier> getPaniers(User owner) {
        return dao.getFromUser(owner);
    }
    public void delete(User owner, Company company){
        Panier panier = dao.getPanier(owner, company);
        dao.delete(panier);
    }
    public Panier getPanierByCompany(User owner, Company company) {
        return dao.getPanier(owner, company);
    }
}
