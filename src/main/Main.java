package main;

import main.FileCreator;
import main.GameProgress;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        FileCreator.createFiles("C:\\Users\\888\\Desktop\\Vano\\Coding\\Netology\\JavaCore\\HW3Games\\Games\\");
        GameProgress save1 = new GameProgress("save1.dat", 8, 4, 3, 32.5);
        GameProgress save2 = new GameProgress("save2.dat", 6, 5, 4, 45.8);
        GameProgress save3 = new GameProgress("save3.dat", 5, 7, 5, 46.7);
        for (GameProgress save : GameProgress.GPList) {
            saveGame(GameProgress.savingDirectory + "savegames//" + save.getId(), save);

            System.out.println(openProgress(GameProgress.savingDirectory + "savegames//" + save.getId()));
        }
        zipFile(GameProgress.savingDirectory + "savegames//packed_GameProgress.zip", GameProgress.pathList);

        openZip(GameProgress.savingDirectory + "savegames//packed_GameProgress.zip", GameProgress.savingDirectory + "unpacked//");

        for (GameProgress save : GameProgress.GPList) {
            File file = new File(GameProgress.savingDirectory + "savegames//" + save.getId());
            if (file.delete()) {
                System.out.println("Исходный Файл " + save.getId() + " удалён");
            }

            System.out.println("распакованный файл " + openProgress(GameProgress.savingDirectory + "unpacked//" + save.getId()));
        }
    }

    public static void saveGame(String path, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String path) {
        GameProgress save = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            save = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return save;
    }


    public static void zipFile(String zipPath, List<String> pathList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (int i = 0; i < pathList.size(); i++) {
                try (FileInputStream fis = new FileInputStream(pathList.get(i))) {
                    ZipEntry entry = new ZipEntry("save" + (i + 1) + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    public static void openZip(String zipPath, String unpacked) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(unpacked + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (
                Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



}

