import java.util.ArrayList;
import java.util.Random;

/**
 * The FortuneTellerBackend class handles the core logic of the Fortune Teller 
 * application. It manages the list of fortunes and handles random generation,
 * additions, and removals with built-in error prevention.
 * * @author Student
 * @version 1.0
 * @date May 16, 2026
 */
public class FortuneTellerBackend {

    /** Holds the list of available fortunes. */
    private ArrayList<String> fortunes;
    
    /** Random object used to select a random index. */
    private Random randomGenerator;

    /**
     * Constructs a FortuneTellerBackend object and initializes it with 10 
     * default fortunes.
     */
    public FortuneTellerBackend() {
        fortunes = new ArrayList<>();
        randomGenerator = new Random();
        initializeDefaultFortunes();
    }

    /**
     * Helper method to populate the ArrayList with 10 pre-made fortunes.
     */
    private void initializeDefaultFortunes() {
        fortunes.add("A thrilling time is in your immediate future.");
        fortunes.add("Your hard work will pay off today.");
        fortunes.add("An old friend will bring you good news shortly.");
        fortunes.add("Do not mistake temptation for opportunity.");
        fortunes.add("You will find hidden treasure where you least expect it.");
        fortunes.add("A smooth sea never made a skilled sailor.");
        fortunes.add("Your patience will lead to great success.");
        fortunes.add("Adventure is waiting for you around the corner.");
        fortunes.add("Believe you can and you are halfway there.");
        fortunes.add("Good things come to those who wait, better things to those who work.");
    }

    /**
     * Selects and returns a random fortune from the list.
     * * @return A randomly selected fortune String.
     * @throws IllegalStateException If the fortune pool is completely empty.
     */
    public String getRandomFortune() throws IllegalStateException {
        if (fortunes.isEmpty()) {
            throw new IllegalStateException("The crystal ball is empty! Add some fortunes first.");
        }
        int randomIndex = randomGenerator.nextInt(fortunes.size());
        return fortunes.get(randomIndex);
    }

    /**
     * Adds a custom new fortune to the list.
     * * @param newFortune The fortune text to be added to the pool.
     * @throws IllegalArgumentException If the provided text is empty or null.
     */
    public void addFortune(String newFortune) throws IllegalArgumentException {
        if (newFortune == null || newFortune.trim().isEmpty()) {
            throw new IllegalArgumentException("Fortune text cannot be empty.");
        }
        fortunes.add(newFortune.trim());
    }

    /**
     * Removes a fortune from the list at the specified index.
     * * @param index The zero-based index of the fortune to remove.
     * @throws IndexOutOfBoundsException If the index is negative or larger than the list size.
     */
    public void removeFortune(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= fortunes.size()) {
            throw new IndexOutOfBoundsException("Invalid index. Please choose a valid fortune number.");
        }
        fortunes.remove(index);
    }

    /**
     * Formats and returns all current fortunes as a single numbered list string.
     * * @return A formatted String representation of all fortunes.
     */
    public String getAllFortunesFormatted() {
        if (fortunes.isEmpty()) {
            return "No fortunes available in the system.";
        }
        
        StringBuilder formattedList = new StringBuilder("--- Current Fortunes ---
");
        for (int i = 0; i < fortunes.size(); i++) {
            formattedList.append(i).append(": ").append(fortunes.get(i)).append("
");
        }
        return formattedList.toString();
    }
}
