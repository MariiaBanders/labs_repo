package lab4;

import java.util.Scanner;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Exchanger<result> eF = new Exchanger<>();
        Exchanger<result> eG = new Exchanger<>();


        System.out.print("Enter the value of x, double: ");
        double x = scanner.nextDouble();

        //getting results for each function
        result f = new result(x);
        result g = new result(x);

        //beginning threads
        Thread ftrd= new Thread(new funcF(eF)); ftrd.start();
        Thread gtrd = new Thread(new funcG(eG)); gtrd.start();

        //synchronization of functions (result for each functions are getting updated)
        eF.exchange(f);
        eG.exchange(g);

        int count = 1;

        //checking time
        while (true) {
            try {
                f = eF.exchange(f, 10*count, TimeUnit.MILLISECONDS);
                g = eG.exchange(g, 10*count, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                System.out.println("Function is working too long. Run anyway?");
                System.out.print("true for run / false for stop ");

                boolean toContinue = scanner.nextBoolean();

                if (toContinue) {
                    count++;
                    continue;
                }

                else {
                    System.out.println("Something went wrong.");
                    ftrd.interrupt();
                    gtrd.interrupt();
                    return;
                }
            }

            break;
        }

        // printing the results
        if (f != null && g != null) {
            System.out.print("f(x) * g(x) = ");
            System.out.println(f.getData() * g.getData());
        }
    }
}
