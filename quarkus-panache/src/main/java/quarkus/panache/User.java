package quarkus.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity(name = "users")
public class User extends PanacheEntity {

    public String username;
    public Boolean active;

}

