package crash.commands.vertx

import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.vertx.java.core.shareddata.SharedData
import org.vertx.mods.VertxCommand

@Usage("interact with the vert.x shared map")
public class sharedmap extends VertxCommand {

  @Usage("list content of a map")
  @Command
  public void keys(
      @Usage("The map")
      @Argument(name =  "map")
      @Required String map) {
    SharedData shared = getVertx().sharedData();
    shared.getMap(map).each { key, value ->
      context.provide([
          key: key,
          value: value
      ]);
    }
  }

  @Usage("get a value")
  @Command
  public String get(
      @Usage("The map")
      @Argument(name =  "map")
      @Required String map,
      @Usage("The key")
      @Argument(name =  "key")
      @Required String key) {
    SharedData shared = getVertx().sharedData();
    return shared.getMap(map).get(key);
  }

  @Usage("put a value")
  @Command
  public void put(
      @Usage("The map")
      @Argument(name =  "map")
      @Required String map,
      @Usage("The key")
      @Argument(name =  "key")
      @Required String key,
      @Usage("The value")
      @Argument(name =  "value")
      @Required String value) {
    SharedData shared = getVertx().sharedData();
    shared.getMap(map).put(key, value);
  }

  @Usage("remove a value")
  @Command
  public void rm(
      @Usage("The map")
      @Argument(name =  "map")
      @Required String map,
      @Usage("The key")
      @Argument(name =  "key")
      @Required String key) {
    SharedData shared = getVertx().sharedData();
    shared.getMap(map).remove(key);
  }

  @Usage("clear a map")
  @Command
  public void clear(
      @Usage("The map")
      @Argument(name =  "map")
      @Required String map) {
    SharedData shared = getVertx().sharedData();
    shared.getMap(map).clear();
  }

  @Usage("destroy a shared map")
  @Command
  public void destroy(
      @Usage("The map")
      @Argument(name =  "map")
      @Required String map) {
    SharedData shared = getVertx().sharedData();
    shared.removeMap(map);
  }
}