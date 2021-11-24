package montp.web;

import montp.data.model.Historique;
import montp.data.model.Quote;
import montp.services.HistoriqueService;
import montp.services.PanierService;
import montp.services.StockMarket;
import montp.services.UserService;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;

@Singleton
@Startup
@LocalBean
public class QuoteSchedule {
    @Inject
    PanierService panierService;
    @Inject
    HistoriqueService historiqueService;
    @Inject
    StockMarket stockMarket;
    @Inject
    UserSession userSession;
    @Inject
    UserService userService;

    @Schedule(second = "0", minute = "*/5", hour = "*")
    public void historise() {
        List<String> companies = panierService.getCompanies();
        for (String company:companies) {
            Double quote = stockMarket.getQuote(stockMarket.getCompany(company));
            Historique historique = new Historique();
            historique.setQuote(quote);
            historique.setCompany(company);
            historiqueService.insert(historique);
        }
    }

    @Schedule(second="*/10",minute="*",hour = "*")
    public void isActive(){
        if(!userService.isActive(userSession.getUser())) userSession.logout();
    }
}
