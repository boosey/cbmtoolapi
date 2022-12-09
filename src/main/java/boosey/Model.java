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

}
