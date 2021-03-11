package database;

import java.util.Base64;
import java.util.Random;

public class ItemID {
  protected String id = "";

  protected static String genID() {
    Random rand = new Random();
    long r = rand.nextLong();
    return new String(Base64.getEncoder().encode(String.valueOf(r).getBytes()));
  }

  @Override
  public boolean equals(Object arg) {
    ItemID item = (ItemID) arg;

    return this.id.equals(item.id);
  }
}
