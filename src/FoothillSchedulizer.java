import java.util.ArrayList;
import java.util.Scanner;

public class FoothillSchedulizer {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        String num;
        int numClass;

        // get user input of number of classes needed
        System.out.print("How many classes do you need to take: ");
        num = in.nextLine();
        numClass = Integer.parseInt(num);

        // get user input of classes needed
        String[] inputClasses = new String[numClass];
        for (int i = 0; i < numClass; i++) {
            System.out.print("Class #" + (i+1) + ": ");
            inputClasses[i] = in.nextLine();
        }

        // put all the valid classes into an ArrayList
        ArrayList<ClassName> validClassList = new ArrayList<>();
        for (int i = 0; i < numClass; i++) {
            int digitCounter = 0;
            for (int j = 0; j < inputClasses[i].length(); j++) {
                if (Character.isDigit(inputClasses[i].charAt(j)))
                    ++digitCounter;
            }

            if (digitCounter == 1)
                inputClasses[i] = "00" + inputClasses[i];
            else if (digitCounter == 2)
                inputClasses[i] = "0" + inputClasses[i];

            if (ClassLoader.classExists(new ClassName(inputClasses[i])))
                validClassList.add(new ClassName(inputClasses[i]));
        }

        // move all the ArrayList elements into an array
        ClassName[] validClasses = new ClassName[validClassList.size()];
        validClasses = validClassList.toArray(validClasses);

        // load all the valid input classes into 2D Class object
        Class[][] classes = ClassLoader.loadClassesWithoutCheck(validClasses);

        // get schedule possibility
        ScheduleChecker scheduleChecker = new ScheduleChecker(classes);
        Schedule[] schedules = scheduleChecker.getPossibleSchedules();

    }
}