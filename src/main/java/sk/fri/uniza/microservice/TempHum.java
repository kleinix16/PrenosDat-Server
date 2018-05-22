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
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "temphums")
@NamedQueries({
    @NamedQuery(
            name = "sk.fri.uniza.microservice.TempHum.findAll",
            query = "SELECT s from TempHum s"
    )
})
public class TempHum {

    @Id
    @GeneratedValue
    private long id;

    @Length(max = 20)
    private String content;

    public TempHum() {
        // Jackson deserialization
        
    }

    public TempHum(String content) {
        this.content = content;
    }

    public TempHum(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.content);
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
        final TempHum other = (TempHum) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return true;
    }
}