package installGame;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InstallGame {
  private final String installationDir = "C:\\JavaFileTaskGame\\";
  private final String[] gameDirectoriesRootingList = new String[]{
    "savegames",
    "temp",
    "src\\main",
    "src\\test",
    "res\\drawables",
    "res\\vectors",
    "res\\icons",
  };
  private final String[] gameFilesListRooting = new String[]{
    "src\\main\\Main.java",
    "src\\main\\Utils.java",
    "src\\main\\Utils.java",
    "temp\\temp.txt",
  };
  private final String logFileRoot = "temp\\temp.txt";
  private StringBuilder installationLog = new StringBuilder("Лог установки игры \n");

  public void initInstallationSequence() {
    initDirectories();
    initFiles();
    logInstallation();
  }

  private void logInstallation() {
    Path path = Paths.get(installationDir+logFileRoot);
    try {
      Files.write(path, installationLog.toString().getBytes());
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  private void initDirectories() {
    for (String dir : gameDirectoriesRootingList) {
      File myDirectory = new File(installationDir+dir);
      if (!myDirectory.exists()) {
        if (myDirectory.mkdirs()) {
          errorLogHandler(myDirectory.getName(), false);
        } else {
          errorLogHandler(myDirectory.getName(), true);
        }
      } else {
        System.out.println(myDirectory.getName() + " Уже присутствует.");
      }
    }
  }

  private void initFiles() {
    for (String file : gameFilesListRooting) {
      File nestedFile = new File(installationDir+file);
      try{
        nestedFile.createNewFile();
        errorLogHandler(nestedFile.getName(), false);
      } catch (IOException error){
        errorLogHandler(nestedFile.getName(), true);
        error.printStackTrace();
      };
    }
  }

  private void errorLogHandler(String fileName, boolean isError) {
    String message = !isError ? "[" + new SimpleDateFormat("HH:mm dd-MM-yyyy").format(new Date()) +  "] " + fileName + " успешно создан." : "Не удалось создать " + fileName;
    installationLog.append("\n " + message);
    System.out.println(message);
  }
}
