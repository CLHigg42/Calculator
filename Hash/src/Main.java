import java.time.Clock;

public class Main {
    public static void main(String[] args) {
        int[] nums ={1,2,2};
        squareSum(nums);
    }
    public static int squareSum(int[] n)
    {
        System.out.println("Here");

        int total = 0;
        for(int i = 0; i < n.length; i++){
            double temp = Math.pow(n[i], 2);
            System.out.println(temp);
            int value = (int)temp;
            total += value;
            System.out.println(total);
        }
        return total;

    }
}