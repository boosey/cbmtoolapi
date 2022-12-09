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
}
