package boosey;

import java.util.ArrayList;
import java.util.UUID;

public class Component {
  public String id;
  public String name = "New Component";
  public String description = "";
  public int strategic = 0;
  public int relationship = 0;
  public String notes = "";
  public boolean isIbmConsulting = false;
  public boolean isIbmTechnology = false;
  public boolean isBusiness = false;
  public boolean isAppDev = false;
  public boolean isOpsInfra = false;
  public String businessContact = "";
  public String appDevContact = "";
  public String opsInfraContact = "";
  public ArrayList<String> tags = new ArrayList<>();

  public Component() {
    this.id = UUID.randomUUID().toString();
  }

  private static void copyComponentData(Component newThis, Component that) {
    newThis.name = that.name;
    newThis.description = that.description;
    newThis.strategic = that.strategic;
    newThis.relationship = that.relationship;
    newThis.notes = that.notes;
    newThis.isIbmConsulting = that.isIbmConsulting;
    newThis.isIbmTechnology = that.isIbmTechnology;
    newThis.isAppDev = that.isAppDev;
    newThis.isBusiness = that.isBusiness;
    newThis.isOpsInfra = that.isOpsInfra;
    newThis.appDevContact = that.appDevContact;
    newThis.opsInfraContact = that.opsInfraContact;
    newThis.businessContact = that.businessContact;
    newThis.tags = new ArrayList<>(that.tags);
  }

  // Create a copy with a new id
  public Component(Component that) {
    this.id = UUID.randomUUID().toString();
    copyComponentData(this, that);
  }

  // Create a copy with the existing id
  // Used when updating an existing one
  public Component(String componentId, Component that) {
    this.id = componentId;
    copyComponentData(this, that);

  }
}
