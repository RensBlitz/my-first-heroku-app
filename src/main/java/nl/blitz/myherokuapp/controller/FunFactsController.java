package nl.blitz.myherokuapp;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/fun")
@CrossOrigin(origins = "*")
public class FunFactsController {

    private final Random random = new Random();
    
    private final List<String> funFacts = List.of(
        "A day on Venus is longer than its year!",
        "Honey never spoils. Archaeologists found 3000-year-old honey in Egyptian tombs!",
        "The first oranges weren't orange - they were green!",
        "A cloud can weigh more than a million pounds!",
        "Octopuses have three hearts!",
        "Bananas are berries, but strawberries aren't!",
        "A flamingo's head has to be upside down when it eats!",
        "The shortest war in history lasted only 38 minutes between Britain and Zanzibar!",
        "The average person spends 6 months of their lifetime waiting for red lights to turn green!",
        "A jiffy is an actual unit of time: 1/100th of a second!",
        "The Great Wall of China is not visible from space with the naked eye!",
        "Cows have best friends and get stressed when separated!",
        "The average person walks the equivalent of five times around the world in their lifetime!",
        "A day on Mars is only 37 minutes longer than a day on Earth!",
        "Honeybees can recognize human faces!",
        "The inventor of the Pringles can was buried in a Pringles can at his request!",
        "A group of flamingos is called a 'flamboyance'!",
        "The shortest commercial flight in the world is in Scotland and takes just 1.5 minutes!",
        "Nintendo was founded in 1889 as a playing card company!",
        "The average person spends 6 years of their life dreaming!"
    );

    private final List<String> jokes = List.of(
        "Why don't programmers like nature? It has too many bugs!",
        "Why did the Java developer need glasses? Because he couldn't C#!",
        "What's a programmer's favorite place? The Cookie Store!",
        "Why do programmers prefer dark mode? Because light attracts bugs!",
        "What did the programmer say to the rubber duck? You're the only one who understands me!",
        "Why do Java developers wear glasses? Because they don't C#!",
        "What's a computer's favorite snack? Microchips!",
        "Why was the math book sad? Because it had too many problems!",
        "What do you call a bear with no teeth? A gummy bear!",
        "Why don't skeletons fight each other? They don't have the guts!"
    );

    @GetMapping("/fact")
    public Map<String, String> getRandomFact() {
        String fact = funFacts.get(random.nextInt(funFacts.size()));
        return Map.of("fact", fact);
    }

    @GetMapping("/joke")
    public Map<String, String> getRandomJoke() {
        String joke = jokes.get(random.nextInt(jokes.size()));
        return Map.of("joke", joke);
    }

    @GetMapping("/time-fact")
    public Map<String, Object> getTimeFact() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        String timeFact = hour < 12 ? "Morning person? Great choice!" :
                         hour < 17 ? "Afternoon productivity peak!" :
                         hour < 22 ? "Evening relaxation time!" :
                         "Night owl mode activated!";
        
        return Map.of(
            "currentTime", now.toString(),
            "timeFact", timeFact,
            "timeZone", java.util.TimeZone.getDefault().getID()
        );
    }

    @GetMapping("/number/{number}")
    public Map<String, Object> getNumberFacts(@PathVariable int number) {
        Map<String, Object> facts = new HashMap<>();
        facts.put("number", number);
        facts.put("isEven", number % 2 == 0);
        facts.put("isPrime", isPrime(number));
        facts.put("square", number * number);
        facts.put("cube", number * number * number);
        facts.put("funFact", generateNumberFunFact(number));
        return facts;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private String generateNumberFunFact(int number) {
        if (number == 42) return "The answer to life, the universe, and everything!";
        if (number == 7) return "Considered lucky in many cultures!";
        if (number == 13) return "Considered unlucky in many Western cultures!";
        if (number == 0) return "The only number that can't be divided or multiply to change another number!";
        if (number == 1) return "The loneliest number!";
        if (number == 3) return "The number of primary colors!";
        if (number == 8) return "A perfect cube (2³) and symbol of luck in Chinese culture!";
        if (number == 9) return "The number of planets in our solar system (sorry Pluto)!";
        if (number == 12) return "Months in a year, hours on a clock face, and eggs in a dozen!";
        if (number == 100) return "Perfect score! Also the number of years in a century!";
        return "Just a regular number, but still special in its own way!";
    }
} 