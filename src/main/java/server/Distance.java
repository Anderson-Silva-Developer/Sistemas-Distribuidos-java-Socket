package server;

public class Distance {
    public Distance() {
    }
    public  int calDistace(int rec[], int player[]){

        int x = Math.abs((rec[0]/50) - (player[0]/50));
        int y = Math.abs((rec[1]/50) - (player[1]/50));
        int result = x+y;
        return result;
    }
}
