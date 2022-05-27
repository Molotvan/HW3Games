package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    public static void createFiles(String path) {
        String[] directories = {"res", "src", "temp", "savegames", "unpacked"};
        StringBuilder report = new StringBuilder();
        for (String directory : directories) {
            File file = new File(path + directory);
            if (file.mkdir()) {
                report.append("Каталог " + directory + " создан");
                report.append("\n");

                if (directory.equals("res")) {
                    String[] resDirectories = {"drawable", "icon", "vector"};
                    for (String resDirectory : resDirectories) {
                        File file2 = new File(path + "\\" + directory + "\\" + resDirectory);
                        if (file2.mkdir()) {
                            report.append("Каталог " + resDirectory + " создан");
                            report.append("\n");
                        }
                    }
                }
                if (directory.equals("src")) {
                    String[] srcDirectories = {"main", "test"};
                    for (String srcDirectory : srcDirectories) {
                        File file2 = new File(path + directory + "\\" + srcDirectory);
                        if (file2.mkdir()) {
                            report.append("Каталог " + srcDirectory + " создан");
                            report.append("\n");
                        }
                        if (srcDirectory.equals("main")) {
                            String[] mainFiles = {"Main.java", "Utils.java"};
                            for (String mainFile : mainFiles) {
                                File file3 = new File(path + directory + "\\" + srcDirectory + "\\" + mainFile);
                                try {
                                    if (file3.createNewFile()) {
                                        report.append("Файл " + mainFile + " создан");
                                        report.append("\n");
                                    }
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }

                            }
                        }
                    }
                }
                if (directory.equals("temp")) {
                    File temptxt = new File(path + "\\" + directory + "\\" + "temp.txt");
                    try {
                        if (temptxt.createNewFile()) {
                            report.append("Файл temp.txt создан");
                            report.append("\n");
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    String reports = report.toString();
                    try (FileWriter reporter = new FileWriter(temptxt)) {
                        reporter.write(reports);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }
}
