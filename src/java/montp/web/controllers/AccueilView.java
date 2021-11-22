package montp.web.controllers;

import montp.data.model.Company;
import montp.data.model.Panier;
import montp.services.PanierService;
import montp.services.StockMarket;
import montp.web.UserSession;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ViewScoped
@Named
public class AccueilView implements Serializable {
    @Inject
    StockMarket stockMarket;
    @Inject
    PanierService panierService;
    @Inject
    UserSession userSession;
    String societe;
    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public List<Company> getCompanies() {
            if (societe=="" || societe==null) return new LinkedList<Company>();
            List<Company> companies = (List<Company>)stockMarket.getCompanies(societe);
            List<Panier> paniers = panierService.getPaniers(userSession.getUser());
            for (Panier panier :paniers) {
                companies.remove(stockMarket.getCompany(panier.getCompany()));
            }
            return companies;
    }

    public void addCompany(Company company){
        Panier newPanier = new Panier();
        newPanier.setOwner(userSession.getUser());
        newPanier.setCompany(company.getSymbol());
        newPanier.setQuote(stockMarket.getQuote(company));
        panierService.insert(newPanier);
    }

    public String getQuote(Company company){
        return String.format("%.2f",stockMarket.getQuote(company));
    }
}
