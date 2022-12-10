package boosey;

import java.util.ArrayList;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

@MongoEntity
public class Model extends ReactivePanacheMongoEntity {
  public String name = "New Model";
  public String description = "";
  public ArrayList<Layer> layers;

  public Model() {
    this.layers = new ArrayList<Layer>();

  }

  public Model(int j) {
    this.layers = new ArrayList<Layer>();
    for (int i = 0; i < j; i++) {
      this.layers.add(new Layer(j));
    }
  }

  public Model(Model that) {
    this.name = that.name;
    this.description = that.description;
    this.layers = new ArrayList<Layer>();
    that.layers.forEach((thatL) -> this.layers.add(new Layer(thatL)));
  }

  public Model addLayer() {
    this.layers.add(new Layer());
    return this;
  }

  public Model addSection(String layerId) {
    this.layers.stream()
        .filter(l -> l.id == layerId)
        .findFirst()
        .ifPresent(l -> l.sections.add(new Section(0)));
    ;
    return this;
  }

  public Model addComponent(String layerId, String sectionId) {
    this.layers.stream()
        .filter(l -> l.id == layerId)
        .findFirst()
        .ifPresent(l -> l.sections.stream()
            .filter(s -> s.id == sectionId)
            .findFirst()
            .ifPresent(s -> s.components.add(new Component())));

    return this;
  }

}
