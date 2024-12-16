package pe.idat.ExamenFInal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "countries")
public class Countries {
    @Id
    private String name;
    private String continent;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Countries(String name, String continent, String language) {
        this.name = name;
        this.continent = continent;
        this.language = language;
    }

    public Countries(String continent, String language) {
        this.continent = continent;
        this.language = language;
    }

    public Countries() {
    }
}
