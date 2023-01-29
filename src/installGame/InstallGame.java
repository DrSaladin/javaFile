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
  private StringBuilder installationLogText = new StringBuilder("Лог установки игры \n");

  public void initInstallationSequence() {
    initDirectories();
    initFiles();
    logInstallation();
  }

  private void logInstallation() {
    Path path = Paths.get(installationDir+logFileRoot);
    try {
      Files.write(path, installationLogText.toString().getBytes());
    } catch (IOException error) {
      System.out.println(error.getMessage());
    }
    System.out.println("Установка игры закончена. \n" + "Лог установки находится C:\\JavaFileTaskGame\\temp\\temp.txt");
  }

  private void initDirectories() {
    for (String dir : gameDirectoriesRootingList) {
      File myDirectory = new File(installationDir+dir);
      if (!myDirectory.exists()) {
        if (myDirectory.mkdirs()) {
          errorLogHandler(myDirectory.getName(), "success");
        } else {
          errorLogHandler(myDirectory.getName(), "error");
        }
      } else {
        errorLogHandler(myDirectory.getName(), "warning");
      }
    }
  }

  private void initFiles() {
    for (String file : gameFilesListRooting) {
      File nestedFile = new File(installationDir+file);
      try{
        nestedFile.createNewFile();
        errorLogHandler(nestedFile.getName(), "success");
      } catch (IOException error){
        errorLogHandler(nestedFile.getName(), "error");
        System.out.println(error.getMessage());
      };
    }
  }

  private void errorLogHandler(String fileName, String stringCode) {
    String message = "";
    String messageCommonPart = "[" + new SimpleDateFormat("HH:mm dd-MM-yyyy").format(new Date()) + "] ";
    switch (stringCode) {
      case "success" ->
        message = messageCommonPart + fileName + " успешно создан.";
      case "error" ->
        message = messageCommonPart + "Не удалось создать " + fileName;
      case "warning" ->
        message = messageCommonPart + fileName + " уже присутствует.";
    }
    installationLogText.append("\n " + message);
    System.out.println(message);
  }
}
