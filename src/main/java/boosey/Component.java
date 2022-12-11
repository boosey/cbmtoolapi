package boosey;

import java.util.UUID;

public class Component {
  public String id;
  public String name = "New Component";
  public String description = "";
  public int strategic = 0;
  public int relationship = 0;
  public String notes = "";

  public Component() {
    this.id = UUID.randomUUID().toString();
  }

  public Component(Component that) {
    this.id = UUID.randomUUID().toString();
    this.name = that.name;
    this.description = that.description;
  }

  public Component(String componentId, Component that) {
    this.id = componentId;
    this.name = that.name;
    this.description = that.description;
  }
}
