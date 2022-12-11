package boosey;

import java.util.ArrayList;
import java.util.UUID;

public class Layer {
  public String id;
  public String name = "New Layer";
  public String description = "";
  public ArrayList<Section> sections;

  public Layer() {
    this.id = UUID.randomUUID().toString();
    this.sections = new ArrayList<Section>();
    this.sections.add(new Section());

  }

  public Layer(int i) {
    this.id = UUID.randomUUID().toString();
    this.sections = new ArrayList<Section>();
    for (int j = 0; j < i; j++) {
      this.sections.add(new Section(i));
    }
  }

  public Layer(Layer that) {
    this.id = UUID.randomUUID().toString();
    this.name = that.name;
    this.description = that.description;
    this.sections = new ArrayList<Section>();
    that.sections.forEach(thatS -> this.sections.add(new Section(thatS)));
  }

  public Layer(String layerId, Layer that) {
    this.id = layerId;
    this.name = that.name;
    this.description = that.description;
    this.sections = new ArrayList<Section>();
    that.sections.forEach(thatS -> this.sections.add(new Section(thatS.id, thatS)));
  }

}
