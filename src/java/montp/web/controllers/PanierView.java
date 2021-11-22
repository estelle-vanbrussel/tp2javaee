package montp.web.controllers;

import montp.data.model.Company;
import montp.data.model.Panier;
import montp.services.PanierService;
import montp.services.StockMarket;
import montp.web.UserSession;

import javax.ejb.Schedule;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named
@ViewScoped
public class PanierView  implements Serializable {
    @Inject
    StockMarket stockMarket;
    @Inject
    PanierService panierService;
    @Inject
    UserSession userSession;

    public List<Company> getCompanies(){
        List<Company> companies = new LinkedList<>();
        List<Panier> paniers = panierService.getPaniers(userSession.getUser());
        for (Panier panier :paniers) {
            companies.add(stockMarket.getCompany(panier.getCompany()));
        }
        return companies;
    }

    public String getQuote(Company company){
        return String.format("%.2f",stockMarket.getQuote(company));
    }

    public String getDiff(Company company){
        Panier panier= panierService.getPanierByCompany(userSession.getUser(),company);
        Double quoteAjout = panier.getQuote();

        Double diff = ((stockMarket.getQuote(company)-quoteAjout)/quoteAjout * 100);
        return String.format("%.2f",diff);
    }
    public void delete(Company company){
        panierService.delete(userSession.getUser(),company);
        getCompanies();
    }
}
