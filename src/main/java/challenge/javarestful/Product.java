package challenge.javarestful;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
class Product {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;

    @OneToOne
    private Album album;

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

    public Album getAlbum(){
        return this.album;
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

    public void setAlbum(Album album){
        this.album = album;
    }
}