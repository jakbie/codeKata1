import java.util.List;
import java.util.stream.Collectors;

public class NegativesNotAllowedException extends RuntimeException {

    public NegativesNotAllowedException(List<Integer> negatives) {
        super("Negatives not allowed: " + negatives.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }

}
