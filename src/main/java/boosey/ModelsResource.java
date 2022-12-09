package boosey;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.RestPath;
import io.smallrye.mutiny.Uni;

@Path("models")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ModelsResource {

  @GET
  public Uni<List<Model>> getAll() {
    return Model.listAll();
  }

  @GET
  @Path("/{modelId}")
  public Uni<Model> getOne(@RestPath String modelId) {
    return Model.findById(new ObjectId(modelId));
  }

  @POST
  public Uni<Void> createOne() {
    return Model.persist(new Model(1));
  }

}