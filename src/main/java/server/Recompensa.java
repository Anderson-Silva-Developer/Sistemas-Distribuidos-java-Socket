package server;

import java.util.Random;

public class Recompensa {
    Random r = new Random();

    public Recompensa() {

    }

    public int[] geraRecompensa() {
        int coordX = r.nextInt(14)*50;
        int coordY = r.nextInt(11)*50;
        int[] xy = {coordX, coordY};
        return xy;

    }
}
