package montp.data.model;

import montp.data.model.security.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Panier extends GenericEntity{
    @ManyToOne
    private User owner;
    private String company;
    private Double quote;

    public Panier() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getQuote() {
        return quote;
    }

    public void setQuote(Double quote) {
        this.quote = quote;
    }
}
