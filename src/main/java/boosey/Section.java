package boosey;

import java.util.ArrayList;
import java.util.UUID;

public class Section {
  public String id;
  public String name = "New Section";
  public String description = "";
  public ArrayList<Component> components;

  public Section() {
    this.id = UUID.randomUUID().toString();
    this.components = new ArrayList<Component>();
  }

  public Section(int i) {
    this.id = UUID.randomUUID().toString();
    this.components = new ArrayList<Component>();
    for (int j = 0; j < i; j++) {
      this.components.add(new Component());
    }
  }
}
