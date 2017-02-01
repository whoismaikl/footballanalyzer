import Services.ImportUtils;
import Services.OutputUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        printMessage();
    }

    public static void printMessage() throws IOException {
        System.out.println("Football Analyzer - Welcome!");
        System.out.println("1 - Import XML Data Files.");
        System.out.println("2 - Display Statistics.");
        System.out.println("3 - Exit.");
        readChoice();
    }

    public static void readChoice() throws IOException {
        String userChoiceString = reader.readLine();
        try {
            Integer userChoice = Integer.parseInt(userChoiceString);
            switch (userChoice) {
                case 1:
                    importData();
                    break;
                case 2:
                    printOutputMessage();
                    break;
                case 3:
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Invalid input.");
                    printMessage();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        } catch (Exception e) {
        }
    }

    private static void importData() throws IOException {
        System.out.println("Enter Directory To Import:");
        String userChoiceString = reader.readLine();
        ImportUtils importUtils = new ImportUtils();
        File folder = new File(userChoiceString);
        importUtils.listFilesForFolder(folder);
        System.out.println("Import Successful!");
        printMessage();
    }

    public static void printOutputMessage() throws IOException {
        System.out.println("Enter Your`s Choice");
        System.out.println("1 - Print Tournament Table.");
        System.out.println("2 - Print Ten Best Tournament Players.");
        System.out.println("3 - Print Tournament Rudest Player.");
        System.out.println("4 - Print Full Statistic About Selected Team.");
        System.out.println("5 - Print Goalkeepers Statistic.");
        System.out.println("6 - Print Best Five Goalkeepers.");
        System.out.println("7 - Print Referees Statistic.");
        System.out.println("8 - Print Player Count In Tournament.");
        System.out.println("9 - Print Team Count In Tournament.");
        System.out.println("10 - Back");
        readOutputChoice();
    }

    private static void readOutputChoice() throws IOException {
        String userChoiceString = reader.readLine();
        OutputUtils outputUtils = new OutputUtils();
        try {
            Integer userChoice = Integer.parseInt(userChoiceString);
            switch (userChoice) {
                case 1:
                    outputUtils.printTournamentTable();
                    printOutputMessage();
                    break;
                case 2:
                    outputUtils.printTenBestPlayers();
                    printOutputMessage();
                    break;
                case 3:
                    outputUtils.printRudestPlayer();
                    printOutputMessage();
                    break;
                case 4:
                    outputUtils.printTeamStatistic();
                    printOutputMessage();
                    break;
                case 5:
                    outputUtils.printAllGoalkeepers();
                    printOutputMessage();
                    break;
                case 6:
                    outputUtils.printBestFiveGoalkeepers();
                    printOutputMessage();
                    break;
                case 7:
                    outputUtils.printReferees();
                    printOutputMessage();
                    break;
                case 8:
                    outputUtils.printPlayerCount();
                    printOutputMessage();
                    break;
                case 9:
                    outputUtils.printTeamCount();
                    printOutputMessage();
                    break;
                case 10:
                    printMessage();
                    break;
                default:
                    System.out.println("Invalid input.");
                    printOutputMessage();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }
}
