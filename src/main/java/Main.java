import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Hanterar användarinmatning, programstatus och datalagring i minnet
        Scanner scanner = new Scanner(System.in);
        boolean igang = true;
        ArrayList<String> uppgifter = new ArrayList<>();

        System.out.println("Välkommen till TaskTracker");
        System.out.print("Vad heter du? ");
        String namn = scanner.nextLine();
        System.out.println("Hej " + namn + "! Nu sätter vi igång.");

        // Huvudloop som håller igång programmet tills användaren väljer att avsluta
        while (igang) {
            System.out.println("\n--- MENY ---");
            System.out.println("1. Visa alla uppgifter");
            System.out.println("2. Lägg till ny uppgift");
            System.out.println("3. Ta bort uppgift");
            System.out.println("4. Avsluta");
            System.out.print("Välj ett alternativ (1-4): ");

            String val = scanner.nextLine();

            // Alternativ 1: Skriver ut alla sparade uppgifter med numrering (1-baserad för användaren)
            if (val.equals("1")) {
                if (uppgifter.isEmpty()) {
                    System.out.println("Listan är tom!");
                } else {
                    System.out.println("Dina uppgifter:");
                    for (int i = 0; i < uppgifter.size(); i++) {
                        System.out.println((i + 1) + ". " + uppgifter.get(i));
                    }
                }

                // Alternativ 2: Lägger till en ny textsträng i dynamiska listan
            } else if (val.equals("2")) {
                System.out.print("Skriv in en ny uppgift: ");
                String nyUppgift = scanner.nextLine();
                uppgifter.add(nyUppgift);
                System.out.println("Uppgiften '" + nyUppgift + "' har sparats.");

                // Alternativ 3: Tar bort uppgift med felhantering för ogiltiga index och textinmatning
            } else if (val.equals("3")) {
                if (uppgifter.isEmpty()) {
                    System.out.println("Det finns inga uppgifter att ta bort!");
                } else {
                    System.out.print("Skriv in numret på uppgiften du vill ta bort: ");
                    try {
                        int nummer = Integer.parseInt(scanner.nextLine());
                        int index = nummer - 1; // Omvandlar användarens nummer till nollbaserat index

                        if (index >= 0 && index < uppgifter.size()) {
                            String borttagen = uppgifter.remove(index);
                            System.out.println("Tog bort uppgiften: '" + borttagen + "'");
                        } else {
                            System.out.println("Ogiltigt nummer, försök igen.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Fel: Du måste skriva en siffra, inte bokstäver!");
                    }
                }

                // Alternativ 4: Sparar alla uppgifter till fil och stänger av loopen
            } else if (val.equals("4")) {
                sparaTillFil(uppgifter);
                System.out.println("Programmet avslutas, hejdå!");
                igang = false;
            } else {
                System.out.println("Ogiltigt val, försök igen med 1, 2, 3 eller 4.");
            }
        }

        scanner.close();
    }

    // Metod som skriver alla element från ArrayList till en extern textfil (uppgifter.txt)
    public static void sparaTillFil(ArrayList<String> uppgifter) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("uppgifter.txt"));
            for (String uppgift : uppgifter) {
                writer.println(uppgift);
            }
            writer.close();
            System.out.println("Uppgifterna har sparats till uppgifter.txt!");
        } catch (IOException e) {
            System.out.println("Kunde inte spara till fil.");
        }
    }
}
