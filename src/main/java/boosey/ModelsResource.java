package boosey;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import io.smallrye.mutiny.Uni;

@Path("models")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class ModelsResource {

  @Inject
  Logger log;

  @GET
  public Uni<List<Model>> getAll() {
    return Model.listAll();
  }

  @GET
  @Path("/{modelId}")
  public Uni<Model> getOne(String modelId, @RestQuery Boolean copy) {

    if (copy != null && copy) {
      log.info("Copying Model");
      return Model.findById(new ObjectId(modelId))
          .onItem().transform((m) -> new Model((Model) m))
          .onItem().<Uni<Model>>transform(m -> ((Model) m).persist())
          .onItem().transformToUni(m -> m);
      // .onItem().<Model>transform((m) -> {
      // log.info("m.id");
      // return m;
      // });
    }
    log.debug("NOT Copying");
    return Model.findById(new ObjectId(modelId));
  }

  @POST
  public Uni<Void> createOne() {
    return Model.persist(new Model(1));
  }

  @DELETE
  @Path("/{modelId}")
  public Uni<Boolean> deleteOne(@RestPath String modelId) {
    return Model.deleteById(new ObjectId(modelId));
  }

}