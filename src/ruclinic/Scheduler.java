package ruclinic;
import java.util.Scanner;

public class Scheduler {
    private static final int COMMAND_INDEX = 0;

    Scanner input;

    public Scheduler()
    {
        System.out.println("Scheduler is running.");
        input = new Scanner(System.in);
        run();
    }

    private void run()
    {
        String [] inputLine = input.nextLine().split(",");
        String userCommand = inputLine[COMMAND_INDEX];

        while (!userCommand.equals("Q"))
        {
            switch (userCommand)
            {
                case "S":
                    runCommandS();
                    break;
                case "C":
                    runCommandC();
                    break;
                case "R":
                    runCommandR();
                    break;
                case "PA":
                    runCommandPA();
                    break;
                case "PP":
                    runCommandPP();
                    break;
                case "PL":
                    runCommandPL();
                    break;
                case "PS":
                    runCommandPS();
                    break;
            }
            inputLine = input.nextLine().split(",");
            userCommand = inputLine[COMMAND_INDEX];
        }
    }

    private void runCommandS()
    {

    }

    private void runCommandC()
    {

    }

    private void runCommandR()
    {

    }

    private void runCommandPA()
    {

    }

    private void runCommandPP()
    {

    }

    private void runCommandPL()
    {

    }

    private void runCommandPS()
    {

    }
}
