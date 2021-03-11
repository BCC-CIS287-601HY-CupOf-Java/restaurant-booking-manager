package database;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.io.*;
import java.util.ArrayList;

public abstract class Database<Type> {
  private String path;

  private static final String DELIMITER = "~";

  public Database(String collection) {
    this.path = collection + ".db";
  }

  protected Type deserialize(String[] data) throws NotImplementedException {
    throw new NotImplementedException("Requires deserialize function");
  }

  protected String[] serialize(Type data) throws NotImplementedException {
    throw new NotImplementedException("Requires serialize function");
  }

  protected final ArrayList<Type> get() throws IOException, NotImplementedException {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(this.path));
      ArrayList<Type> list = new ArrayList<>();
      String line = reader.readLine();

      while (line != null) {
        String[] items = line.split(Database.DELIMITER);
        list.add(this.deserialize(items));
        line = reader.readLine();
      }
      reader.close();

      return list;
    } catch (FileNotFoundException e) {
      File f = new File(this.path);
      f.createNewFile();
      return this.get();
    }
  }

  protected void set(ArrayList<Type> data) throws IOException, NotImplementedException {
    try {
      FileWriter writer = new FileWriter(this.path, false);

      for (Type item : data) {
        String[] items = this.serialize(item);
        String line = String.join(Database.DELIMITER, items);
        writer.append(line + "\n");
      }

      writer.flush();
      writer.close();
    } catch (FileNotFoundException e) {
      File f = new File(this.path);
      f.createNewFile();
      this.set(data);
    }
  }
}
