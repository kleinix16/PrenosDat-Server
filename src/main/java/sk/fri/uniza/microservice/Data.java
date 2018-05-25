/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

/**
 *
 * @author klein, simon
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "datas")
@NamedQueries({
    @NamedQuery(
            name = "sk.fri.uniza.microservice.Data.findAll",
            query = "SELECT s from Data s"
    )
})
public class Data implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Length(max = 20)
    private String dateAndTime;
    
    private int temp;
    
    private int hum;

    public Data() {
        // Jackson deserialization   
    }

    public Data(String date, int temp, int hum) {
        this.dateAndTime = date;
        this.temp = temp;
        this.hum = hum;
    }

    public Data(long id, String date, int temp, int hum) {
        this.id = id;
        this.dateAndTime = date;
        this.temp = temp;
        this.hum = hum;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.dateAndTime = date;
    }

    @JsonProperty
    public String getDate() {
        return dateAndTime;
    }
    
    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
    
    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.dateAndTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Data other = (Data) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.dateAndTime, other.dateAndTime)) {
            return false;
        }
        return true;
    }

    Optional<Data> findById(Long get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}