package boosey;

import java.util.ArrayList;
import org.bson.types.ObjectId;
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

  // Create Updated Model
  public Model(String modelId, Model that) {
    this.id = new ObjectId(modelId);
    this.name = that.name;
    this.description = that.description;
    this.layers = new ArrayList<Layer>();
    that.layers.forEach((thatL) -> this.layers.add(new Layer(thatL.id, thatL)));
  }

  // Copy Model
  public Model(Model that) {
    this.name = that.name;
    this.description = that.description;
    this.layers = new ArrayList<Layer>();
    that.layers.forEach((thatL) -> this.layers.add(new Layer(thatL)));
  }

  public Model addLayer() {
    this.layers.add(0, new Layer(1));
    return this;
  }

  public String deleteLayer(String lid) {
    if (layers.size() > 1) {
      layers.removeIf(l -> isTheId(l.id, lid));
      return "1";
    }

    return "0";
  }

  public String deleteSection(String lid, String sid) {
    layers.stream()
        .filter(l -> l.id.equalsIgnoreCase(lid)).findFirst()
        .ifPresent(l -> l.deleteSection(sid));

    return "1";
  }

  public String deleteComponent(String lid, String sid, String cid) {
    layers.stream()
        .filter(l -> l.id.equalsIgnoreCase(lid)).findFirst()
        .ifPresent(l -> l.deleteComponent(sid, cid));

    return "1";
  }

  private boolean isTheId(String id1, String id2) {
    var b = id1.equalsIgnoreCase(id2);
    return b;
  }

  public Model addSection(String layerId) {
    this.layers.stream()
        .filter(
            l -> {
              boolean r = l.id.equals(layerId);
              return r;
            })
        .findFirst()
        .ifPresentOrElse(l -> l.sections.add(
            new Section(1)),
            () -> {
              new Exception("Adding Section Layer not found: " + layerId);
            });
    ;
    return this;
  }

  public Model addComponent(String layerId, String sectionId) {
    this.layers.stream()
        .filter(l -> l.id.equals(layerId))
        .findFirst()
        .ifPresent(l -> l.sections.stream()
            .filter(s -> s.id.equals(sectionId))
            .findFirst()
            .ifPresent(s -> s.components.add(new Component())));

    return this;
  }

}
