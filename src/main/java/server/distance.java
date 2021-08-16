package server;

public class distance {
    
    public distance(){

    }

    public static int calDistace(int rec[], int player[]){
        
        int x = Math.abs((rec[0]/50) - (player[0]/50));
        int y = Math.abs((rec[1]/50) - (player[1]/50));

        int result = x+y;
    

        return result;
    }

    public static void main(String[] args) {
        int test[] = {200,200};
        int test2[] = {200,200};
        
        System.out.println(calDistace(test,test2));
    }

    

}
