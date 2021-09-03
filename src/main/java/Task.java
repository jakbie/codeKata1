import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Task {

    public static int add(String arg) {

        if(arg.isBlank()) {
            return 0;
        }


        if(!arg.startsWith("//")) {
            Pattern delimiterPattern = Pattern.compile("[\n,]");

            String[] numbers = getArrayOfStringNumbersFromDelimitersPattern(arg, delimiterPattern);

            return sumNumbersFromStringArray(numbers);
        }


        int newLinePosition = arg.indexOf("\n");
        String delimitersPart = arg.substring(2,newLinePosition);
        String delimitersPatternString;

        if(delimitersPart.length() == 1) {
            delimitersPatternString = Pattern.quote(arg.substring(2,newLinePosition));
        } else {
            Matcher matcher = Pattern.compile("\\[(.*?)\\]").matcher(delimitersPart);

            List<String> delimiters = matcher.results().map(matchResult -> matchResult.group(1)).collect(Collectors.toList());

            delimitersPatternString = delimiters.stream()
                    .map(delimPat -> "\\Q" + delimPat + "\\E")
                    .collect(Collectors.joining("|"));
        }


        Pattern delimitersPattern = Pattern.compile(delimitersPatternString);
        String numbersPart = arg.substring(newLinePosition+1);

        String[] numbers = getArrayOfStringNumbersFromDelimitersPattern(numbersPart, delimitersPattern);

        return sumNumbersFromStringArray(numbers);
    }


    private static String[] getArrayOfStringNumbersFromDelimitersPattern(String string, Pattern pattern) {
        return string.split(pattern.pattern());
    }


    private static int sumNumbersFromStringArray(String [] numbers) {
        checkNegatives(numbers);
        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .filter(num -> num <= 1000)
                .sum();
    }


    private static void checkNegatives(String[] numbers) throws NegativesNotAllowedException {
        List<Integer> negatives = Arrays.stream(numbers)
                .map(Integer::parseInt)
                .filter(num -> num < 0)
                .collect(Collectors.toList());

        if(!negatives.isEmpty()) throw new NegativesNotAllowedException(negatives);
    }

}