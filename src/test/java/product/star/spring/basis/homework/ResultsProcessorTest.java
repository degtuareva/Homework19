package product.star.spring.basis.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResultsProcessorTest {

    private ResultsProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new ResultsProcessor();
    }

    @Test
    void testLoadResultsAndGetTopRunners() throws Exception {
        String csv =
                "Иван Иванов, М, 10 км, 55:20\n" +
                        "Мария Петрова, Ж, 5 км, 25:10\n" +
                        "Петр Сергеев, М, 10 км, 52:40\n";

        processor.loadResults(new ByteArrayInputStream(csv.getBytes()));

        List<RunnerResult> top = processor.getTopRunners("10 км", "М", 2);

        assertEquals(2, top.size());
        assertEquals("Петр", top.get(0).getFirstName());
        assertEquals("Иван", top.get(1).getFirstName());
    }
}
