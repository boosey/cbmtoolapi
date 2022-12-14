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

  // Create a copy with a new id
  public Component(Component that) {
    this.id = UUID.randomUUID().toString();
    this.name = that.name;
    this.description = that.description;
    this.strategic = that.strategic;
    this.relationship = that.relationship;
    this.notes = that.notes;
  }

  // Create a copy with the existing id
  // Used when updating an existing one
  public Component(String componentId, Component that) {
    this.id = componentId;
    this.name = that.name;
    this.description = that.description;
    this.strategic = that.strategic;
    this.relationship = that.relationship;
    this.notes = that.notes;
  }
}
