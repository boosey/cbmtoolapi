package boosey;

import java.util.HashSet;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

@MongoEntity
public class Tags extends ReactivePanacheMongoEntity {
  public HashSet<String> options = new HashSet<>();

  public void add(String t) {
    options.add(t);

  }

  public void delete(String t) {
    options.remove(t);
  }
}
