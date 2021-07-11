package challenge.javarestful;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
class Product {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;

    @OneToMany(targetEntity=Album.class)
    private List<Album> albums = new ArrayList<Album>();

    Product() {}

    Product(String name, String description){
        this.name = name;
        this.description = description;
    }

    // getters
    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public List<Album> getAlbums(){
        return this.albums;
    }

    // setters
    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setAlbums(List<Album> albums){
        this.albums = albums;
    }
}