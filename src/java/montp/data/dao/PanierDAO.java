package montp.data.dao;

import montp.data.model.Company;
import montp.data.model.Panier;
import montp.data.model.security.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PanierDAO extends GenericDAO<Panier> {
    public PanierDAO() {
        super(Panier.class);
    }

    public List<Panier> getFromUser(User owner) {
        return em.createQuery("SELECT p FROM Panier p WHERE p.owner=:owner")
                .setParameter("owner", owner)
                .getResultList();
    }

    public Panier getPanier(User owner, Company company){
        return (Panier) em.createQuery("SELECT p FROM Panier p WHERE p.owner=:owner AND p.company=:company")
                .setParameter("owner", owner)
                .setParameter("company", company.getSymbol())
                .getSingleResult();
    }
}
