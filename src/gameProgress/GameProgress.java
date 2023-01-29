package gameProgress;
import java.io.Serializable;

public class GameProgress implements Serializable {
//  private static final long serialVersionUID = 1L;

  private int health;
  private int weapons;
  private int lvl;
  private double distance;
  private String userName;

  public GameProgress(int health, int weapons, int lvl, double distance, String userName) {
    this.health = health;
    this.weapons = weapons;
    this.lvl = lvl;
    this.distance = distance;
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  @Override
  public String toString() {
    return "GameProgress{" +
      "health=" + health +
      ", weapons=" + weapons +
      ", lvl=" + lvl +
      ", distance=" + distance +
      '}';
  }
}
