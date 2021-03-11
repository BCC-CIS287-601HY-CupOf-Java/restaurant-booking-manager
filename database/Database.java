package database;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.io.*;
import java.util.ArrayList;

/**
 * Here I've defined a class that will conduct the file interactions for the
 * database. This is a generic database interface as can be seen by the `<Type>`
 * parameter, this will supply the exact datatype of the content. This class
 * also defines 2 "abstract" methods that need to be defined later.
 * Database.deserialize is a function that will take lines from the database
 * file and convert them to Objects of type Type, in this state it throws a
 * NotImplementedException because the database should not be used directly.
 * Similarly the Database.serialize function will convert Objects into an array
 * of strings to be written to the file. The ordering of the properties of the
 * Type should be maintained between both override implementations.
 * 
 * You will also note that all the member functions here are marked "protected".
 * This means that they are only avaliable to whatever object extends Database.
 * Once that object extends Database, all protected functions will be made
 * private.
 */

public abstract class Database<Type> {
  private String path;

  // DELIMITER should be something that won't be in the value of an object
  private static final String DELIMITER = "~";

  public Database(String collection) {
    this.path = collection + ".db";
  }

  // Creates an Object from a database line, REQUIRES IMPLEMENTATION
  protected Type deserialize(String[] data) throws NotImplementedException {
    throw new NotImplementedException("Requires deserialize function");
  }

  // Reduces an Object to a sequence of strings, REQUIRES IMPLEMENTATION
  protected String[] serialize(Type data) throws NotImplementedException {
    throw new NotImplementedException("Requires serialize function");
  }

  // Reads the database
  protected final ArrayList<Type> get() throws IOException, NotImplementedException {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(this.path));
      ArrayList<Type> list = new ArrayList<>();
      String line = reader.readLine(); // Initalize line so that we can test if there's somthing in the database

      while (line != null) {
        String[] items = line.split(Database.DELIMITER);
        list.add(this.deserialize(items));
        line = reader.readLine();
      }
      reader.close();

      return list;
    } catch (FileNotFoundException e) {
      // Recovery clause creates a new file then tries again
      File f = new File(this.path);
      f.createNewFile();
      return this.get();
    }
  }

  // Deletes and rewrites the database
  protected void set(ArrayList<Type> data) throws IOException, NotImplementedException {
    try {
      FileWriter writer = new FileWriter(this.path, false); // False will overwrite the file

      // This is a for-each loop, it will go over every item in data putting it in
      // Type item without having to setup a counter
      // Zobbi never went over that in any detail
      for (Type item : data) {
        String[] items = this.serialize(item);
        String line = String.join(Database.DELIMITER, items);
        writer.append(line + "\n");
      }

      writer.flush(); // Up until now any writing being done was only in memory, this flushed the
                      // memory into the file. Writing directly to the file is slow so we need to do
                      // this for performance reasons.
      writer.close();
    } catch (FileNotFoundException e) {
      // Recovery clause creates a new file then tries again
      File f = new File(this.path);
      f.createNewFile();
      this.set(data);
    }
  }
}
