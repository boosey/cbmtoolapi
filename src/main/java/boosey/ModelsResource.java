package boosey;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.RestQuery;
import io.smallrye.mutiny.Uni;

@Path("models")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class ModelsResource {

  @GET
  public Uni<List<Model>> getAllModels() {
    return Model.listAll();
  }

  @GET
  @Path("/{modelId}")
  public Uni<Model> getModel(String modelId, @RestQuery Boolean copy) {

    if (copy != null && copy) {

      return Model.findById(new ObjectId(modelId))
          .onItem().transform((m) -> new Model((Model) m))
          .onItem().<Uni<Model>>transform(m -> ((Model) m).persist())
          .onItem().transformToUni(m -> m);
    }

    return Model.findById(new ObjectId(modelId));
  }

  @POST
  public Uni<Void> createModel() {
    return Model.persist(new Model(1));
  }

  @PUT
  @Path("/{modelId}")
  public Uni<Model> updateModel(String modelId, Model model) {
    return Model.<Model>findById(new ObjectId(modelId))
        .onItem().transform(m -> new Model(modelId, model))
        .onItem().transform(newM -> newM.<Model>update())
        .onItem().transformToUni(newM -> newM);
  }

  @DELETE
  @Path("/{modelId}")
  public Uni<Boolean> deleteModel(String modelId) {
    return Model.deleteById(new ObjectId(modelId));
  }

  @DELETE
  @Path("/{modelId}/layers/{layerId}")
  public Uni<Model> deleteLayer(String modelId, String layerId) {

    return Model.<Model>findById(new ObjectId(modelId))
        .onItem().invoke(m -> m.deleteLayer(layerId))
        .onItem().transform(m -> m.<Model>update())
        .onItem().transformToUni(m -> m);
  }

  @DELETE
  @Path("/{modelId}/layers/{layerId}/sections/{sectionId}")
  public Uni<Model> deleteSection(String modelId, String layerId, String sectionId) {

    return Model.<Model>findById(new ObjectId(modelId))
        .onItem().invoke(m -> m.deleteSection(layerId, sectionId))
        .onItem().transform(m -> m.<Model>update())
        .onItem().transformToUni(m -> m);
  }

  @DELETE
  @Path("/{modelId}/layers/{layerId}/sections/{sectionId}/components/{componentId}")
  public Uni<Model> deleteComponent(String modelId, String layerId, String sectionId, String componentId) {

    return Model.<Model>findById(new ObjectId(modelId))
        .onItem().invoke(m -> m.deleteComponent(layerId, sectionId, componentId))
        .onItem().transform(m -> m.<Model>update())
        .onItem().transformToUni(m -> m);
  }

  @POST
  @Path("/{modelId}/layers/{layerId}/{sections}")
  public Uni<Model> createSection(String modelId, String layerId) {
    return Model.<Model>findById(new ObjectId(modelId))
        .onItem().invoke(m -> m.addSection(layerId))
        .onItem().transform(m -> m.<Model>update())
        .onItem().transformToUni(m -> m);
  }

  @POST
  @Path("/{modelId}/layers")
  public Uni<Model> createLayer(String modelId) {
    return Model.<Model>findById(new ObjectId(modelId))
        .onItem().invoke(m -> m.addLayer())
        .onItem().transform(m -> m.<Model>update())
        .onItem().transformToUni(m -> m);
  }

  @POST
  @Path("/{modelId}/layers/{layerId}/sections/{sectionId}/components")
  public Uni<Model> createComponent(String modelId, String layerId, String sectionId) {
    return Model.<Model>findById(new ObjectId(modelId))
        .onItem().invoke(m -> m.addComponent(layerId, sectionId))
        .onItem().transform(m -> m.<Model>update())
        .onItem().transformToUni(m -> m);
  }

  @GET
  @Path("/tags")
  public Uni<Set<String>> getTags() {

    return Tags.<Tags>listAll()
        .onItem().transform((t) -> t.size() > 0 ? t.get(0) : new Tags())
        .onItem().transform((t) -> t.options);
  }

  @POST
  @Path("/tags")
  @Consumes(MediaType.TEXT_PLAIN)
  public Uni<Void> addTag(String newOption) {

    return Tags.<Tags>listAll()
        .onItem().transform((t) -> t.size() > 0 ? t.get(0) : new Tags())
        .onItem().invoke((t) -> t.add(newOption))
        .onItem().transformToUni((t) -> Tags.persistOrUpdate(t));
  }

  @DELETE
  @Path("/tags/{tag}")
  public Uni<Void> deleteTag(String tag) {

    return Tags.<Tags>listAll()
        .onItem().transform((t) -> t.size() > 0 ? t.get(0) : new Tags())
        .onItem().invoke((t) -> t.delete(tag))
        .onItem().transformToUni((t) -> Tags.persistOrUpdate(t));

  }

}