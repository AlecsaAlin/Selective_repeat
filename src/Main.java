
import java.util.Scanner;

public class Main {
    public static void solution(int noPack, Scanner sender)
    {

    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("How many packages you want to send");
        int n = sc.nextInt();
        Sender sender=new Sender("src/message.txt");
        sender.display();
    }
}