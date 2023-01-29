package gameProgressSaving;
import gameProgress.GameProgress;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgressSaving {
  String zippedFileName = "zipped_progress.zip";
  public GameProgressSaving() {}
  public void saveGame(GameProgress[] playersProgress) {
    ArrayList<String> saveFilesPathsList = new ArrayList<String>();

    for(GameProgress playerProgress : playersProgress) {
      String saveFilePath = "C:\\JavaFileTaskGame\\savegames\\save-"+playerProgress.getUserName()+".dat";

      saveFilesPathsList.add(saveFilePath);

      try(FileOutputStream fos = new FileOutputStream(saveFilePath)) {
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(playerProgress.toString());
      } catch (Exception error) {
        System.out.println(error.getMessage());
      }
    }
    zipFiles("C:\\JavaFileTaskGame\\savegames\\", saveFilesPathsList);
  }

  private void zipFiles(String saveFilesDirectory, ArrayList<String> saveFilesPathsList) {
      try (
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(saveFilesDirectory+zippedFileName))) {
        for (String path : saveFilesPathsList) {
          File srcFile = new File(path);
          FileInputStream fis = new FileInputStream(srcFile);
          zout.putNextEntry(new ZipEntry(srcFile.getName()));

          byte[] buffer = new byte[fis.available()];
          int length;

          while ((length = fis.read(buffer)) > 0) {
            zout.write(buffer, 0, length);
          }

          zout.closeEntry();
          fis.close();
        }
        zout.closeEntry();
      } catch (Exception error) {
        System.out.println(error.getMessage());
      }

      Path saveFilesDirectoryPath = Path.of(saveFilesDirectory);
      if (Files.isDirectory(saveFilesDirectoryPath)) {
        try (DirectoryStream<Path> directory = Files.newDirectoryStream(saveFilesDirectoryPath)) {
          for (Path entry: directory) {
            File folder = new File(entry.toString());

            if (!folder.getName().equals(zippedFileName)) {
              folder.delete();
            }
          }
        } catch (IOException error) {
          System.out.println(error.getMessage());
        }
      }
    }
}
