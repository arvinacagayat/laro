import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

 

public class TaskPerf6 {
    private static Scanner scan = new Scanner(System.in);
    
    public static boolean containsSymbolOrNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetterOrDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        boolean option = true;
        while (option) {
            try {
                System.out.println("Please select an option: ");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter choice here: ");
                int choice = scan.nextInt();
                if (choice == 1) {
                    Register();
                } else if (choice == 2) {
                    Login();
                } else if (choice == 3) {
                    System.out.println("THANKYOU!!!");
                    option = false;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("YOU CAN ONLY ENTER NUMBERS!!");
                scan.nextLine();
            }
        }
    }

 

    public static void Register() {
        Path file =
            Paths.get("C:\\Users\\Arvina\\OneDrive\\Documents\\NetBeansProjects\\TaskPerf6\\src\\accounts.txt");
        try (OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, APPEND));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output))) {
            System.out.print("Enter Account Username: ");
            String username = scan.next();
            if (!containsSymbolOrNumber(username)) {
                throw new IllegalArgumentException("Username must contain at least one symbol or number.");
            }
            System.out.print("Enter Account Password: ");
            String password = scan.next();
            if (password.length() < 8 || password.length() > 20) {
                throw new IllegalArgumentException("Password must be between 8 and 20 characters.");
            }
            String details = username + "," + password;
            writer.write(details);
            writer.newLine();
            System.out.println("SUCCESSFULLY REGISTERED");
            System.out.println("------------------------------------------");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("------------------------------------------");
            return;
        } catch (IOException e) {
            System.out.println("Error encountered!");
            System.out.println("------------------------------------------");
        }
    }

 

    public static void Login(){
        Path file = Paths.get("accounts.txt");
        try {
            InputStream input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.print("Enter username: ");
            String username = scan.next();
            if (!containsSymbolOrNumber(username)) {
                throw new IllegalArgumentException("Username must contain at least one symbol or number.");
            }
            System.out.print("Enter password: ");
            String password = scan.next();
           if (password.length() < 8 || password.length() > 20) {
                throw new IllegalArgumentException("Password must be between 8 and 20 characters.");
            }
            String credentials;
            int detailsfound = 0;
            while((credentials = reader.readLine()) != null){
                String[] info = credentials.split(",");
                if (info[0].equals(username)){
                    detailsfound++;
                    break;
                }
            }
            if (detailsfound == 1){
                System.out.println("LOGIN SUCCESSFUL!");
            } else {
                System.out.println("Please try again. Invalid username or password!");
            }
            System.out.println("------------------------------------------");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("------------------------------------------");
            
        } catch (Exception e){

 

        }
    }
}
