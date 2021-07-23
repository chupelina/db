package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter user and password separated by space:");
        String input = scan.nextLine();
        String user = input.split(" ")[0];
        String password = input.split(" ")[1];
        Homework homework = new Homework();
        homework.setConnection(user, password);
        System.out.println("Enter the task number you want to check, if you want to quit type END: ");
        input = scan.nextLine();

        while (!input.equals("END")) {
            int task = Integer.parseInt(input);
            switch (task) {
                case 2:
                    System.out.println("And the villains are:");
                    homework.getVillainsNames();
                    break;
                case 3:
                    System.out.println("Please, enter villain id:");
                    homework.getVillainMinions(scan.nextLine());
                    break;
                case 4:
                    System.out.println("Please, enter the names of the minion and villain that you want to add:");
                    homework.addMinion(scan.nextLine(), scan.nextLine());
                    break;
                case 5:
                    System.out.println("Please, enter the country that you want to change the towns of:");
                    homework.changesTowns(scan.nextLine());
                    break;
                case 6:
                    System.out.println("Please, enter the villain id you want to remove:");
                    homework.removeVillain(scan.nextLine());
                    break;
                case 7:
                    System.out.println("And all minions are:");
                    homework.printAllMinions();
                    break;
                case 8:
                    System.out.println("Enter the minions that you want to change separated by space:");
                    homework.increaseAge(scan.nextLine());
                    break;
                case 9:
                    System.out.println("Please, enter the minion id you want to change:");
                    homework.procedure(scan.nextLine());
                    break;
            }

            System.out.println();
            System.out.println("Enter the task number you want to check, if you want to quit type END: ");
            input = scan.nextLine();
        }
        System.out.println("Thanks for your time :)");
    }
}
