package product.star.spring.basis.homework;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultsProcessor {

    private final List<RunnerResult> results = new ArrayList<>();

    public void loadResults(InputStream csvInputStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvInputStream))) {
            results.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                String[] name = parts[0].trim().split(" ");
                if (name.length < 2) continue;

                String lastName = name[0];
                String firstName = name[1];
                String gender = parts[1].trim();
                String distance = parts[2].trim();
                Duration time = parseTime(parts[3].trim());

                results.add(new RunnerResult(lastName, firstName, gender, distance, time));
            }
        }
    }

    public List<RunnerResult> getTopRunners(String distance, String gender, int n) {
        return results.stream()
                .filter(r -> r.getDistance().equals(distance) && r.getGender().equals(gender))
                .sorted(Comparator.comparing(RunnerResult::getTime))
                .limit(n)
                .collect(Collectors.toList());
    }

    private Duration parseTime(String timeStr) {
        String[] parts = timeStr.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return Duration.ofMinutes(minutes).plusSeconds(seconds);
    }
}