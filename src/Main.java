
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("How many packages you want to send");
        int noPack = sc.nextInt();
        Sender sender=new Sender(noPack);
        System.out.println("Enter sliding window size:");
        int windowSize = sc.nextInt();
        while(windowSize>noPack/2) {
            System.out.println("Enter a smaller sliding window size:");
            windowSize=sc.nextInt();
        }
        sender.send(windowSize);

    }
}