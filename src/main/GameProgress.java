package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String savingDirectory = "C:\\Users\\888\\Desktop\\Vano\\Coding\\Netology\\JavaCore" +
            "\\HW3Games\\Games\\";//ввести своё значение
    private int health;
    private int weapons;
    private int lvl;
    private double distance;
    private String id;
    public static List<String> pathList = new ArrayList<String>();
    public static List<GameProgress> GPList = new ArrayList<GameProgress>();


    public GameProgress(String id, int health, int weapons, int lvl, double distance) {
        this.id = id;
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
        pathList.add(savingDirectory + "savegames//" + id);
        GPList.add(this);
    }

    @Override
    public String toString() {
        return "main.GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public String getId() {
        return this.id;
    }
}