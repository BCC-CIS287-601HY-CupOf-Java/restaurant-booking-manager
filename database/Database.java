package database;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.io.*;
import java.util.ArrayList;

public abstract class Database<Type> {
  private String path;

  private static final String DELIMITER = "~";

  public Database(String collection) throws FileNotFoundException {
    this.path = collection;
  }

  protected Type deserialize(String[] data) throws NotImplementedException {
    throw new NotImplementedException("Requires deserialize function");
  }

  protected String[] serialize(Type data) throws NotImplementedException {
    throw new NotImplementedException("Requires serialize function");
  }

  public final ArrayList<Type> get() throws IOException, NotImplementedException {
    BufferedReader reader = new BufferedReader(new FileReader(this.path));
    ArrayList<Type> list = new ArrayList<>();
    String line;

    do {
      line = reader.readLine();
      String[] items = line.split(Database.DELIMITER);

      list.add(this.deserialize(items));
    } while (line != null);
    reader.close();
    
    return list;
  }

  public void set(ArrayList<Type> data) throws IOException, NotImplementedException {
    FileWriter writer = new FileWriter(this.path, false);

    for (Type item : data) {
      String[] items = this.serialize(item);
      
      for (Integer i = 0; i < items.length; i++) {
        items[i] = "\"" + items[i] + "\"";
      }

      String line = String.join(Database.DELIMITER, items);
      writer.append(line);
    }

    writer.flush();
    writer.close();
  }
}
