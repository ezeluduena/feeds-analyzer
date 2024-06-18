package namedEntities;

import java.util.List;

import namedEntities.enums.Category;
import namedEntities.enums.Topic;

public abstract class NamedEntity {
    protected String nombre;
    protected Category categoria;
    protected List<Topic> topicos;

    // Constructor
    public NamedEntity(String nombre, Category categoria, List<Topic> topicos) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.topicos = topicos;
    }

    // Métodos para agregar y obtener tópicos
    public void addTopic(Topic topico) {
        topicos.add(topico);
    }

    public List<Topic> getTopics() {
        return topicos;
    }

    // Otros métodos getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Category getCategory() {
        return categoria;
    }

    public void setCategory(Category categoria) {
        this.categoria = categoria;
    }

    // Método para imprimir la información de la entidad
    public void imprimirInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Categoría: " + categoria.toString());
        System.out.println("Tópicos: " + topicos.toString());
    }
}