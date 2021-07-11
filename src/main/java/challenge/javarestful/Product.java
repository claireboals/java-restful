package challenge.javarestful;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Product {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private List<Album> albums;

    Product() {}

    Product(String name, String description, List<Album> albums){
        this.name = name;
        this.description = description;
        this.albums = albums;
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