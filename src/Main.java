import gameProgress.GameProgress;
import gameProgressSaving.GameProgressSaving;
import installGame.InstallGame;

public class Main {
  public static void main(String[] args) {
    InstallGame installGame = new InstallGame();
    installGame.initInstallationSequence();

    GameProgressSaving gameProgressSaving = new GameProgressSaving();
    gameProgressSaving.saveGame(new GameProgress[]{
      new GameProgress(94, 10, 2, 254.01, "ObamaSlapUrMama"),
      new GameProgress(95,11,3,255.01, "ManenIron"),
      new GameProgress(96,12,4,256.01, "KiKi")
    });
  }
}
