package product.star.spring.basis.homework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;

@Getter
@AllArgsConstructor
@ToString
public class RunnerResult {
    private String lastName;
    private String firstName;
    private String gender;
    private String distance;
    private Duration time;
}
