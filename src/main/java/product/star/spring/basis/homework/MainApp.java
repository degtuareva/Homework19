package product.star.spring.basis.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

    @Autowired
    private ResultsProcessor resultsProcessor;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (InputStream is = new FileInputStream("results.csv")) {
            resultsProcessor.loadResults(is);
        }

        List<RunnerResult> top = resultsProcessor.getTopRunners("10 км", "М", 3);
        System.out.println("Топ 3 мужчины на 10 км:");
        top.forEach(System.out::println);
    }
}