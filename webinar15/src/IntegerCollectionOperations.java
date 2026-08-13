import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//●	Дана коллекция Collection<Integer> col. С помощью stream API:
//○	найти минимальное число
//○	найти максимальное число
//○	найти среднее арифметическое чисел
//○	найти произведение всех чисел
//○	найти сумму всех чисел
//○	найти сумму всех цифр

public class IntegerCollectionOperations {

    public static void main(String[] args) {
        Collection<Integer> col = List.of(5, 10, 15, 20, 25);

        // Найти минимальное число
        Integer min = col.stream().min(Integer::compareTo).orElse(null);
        System.out.println("Минимальное число: " + min);

        // Найти максимальное число
        Integer max = col.stream().max(Integer::compareTo).orElse(null);
        System.out.println("Максимальное число: " + max);

        // Найти среднее арифметическое чисел
        Double average = col.stream().collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("Среднее арифметическое чисел: " + average);

        // Найти произведение всех чисел
        Integer product = col.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Произведение всех чисел: " + product);

        // Найти сумму всех чисел
        Integer sum = col.stream().reduce(0, Integer::sum);
        System.out.println("Сумма всех чисел: " + sum);

        // Найти сумму всех цифр
        Integer digitSum = col.stream()
                .mapToInt(Integer::intValue)
                .map(Math::abs)
                .map(i -> ((Integer)i).toString().chars().map(Character::getNumericValue).sum())
                .sum();
        System.out.println("Сумма всех цифр: " + digitSum);



    }
}

