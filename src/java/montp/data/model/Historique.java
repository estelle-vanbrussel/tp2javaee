package montp.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Historique extends GenericEntity{
    @Column(nullable = false, columnDefinition = "TEXT NOT NULL")
    String company;
    @Column(nullable = false)
    Double quote;
    @Temporal(TemporalType.TIMESTAMP)
    Date date = new Date();
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean email = false;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public Historique() {
    }
}
