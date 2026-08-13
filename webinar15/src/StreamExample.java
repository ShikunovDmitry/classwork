import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//●	Создать список строк List<String> myList = Arrays.asList("a1", "a2", "a3", "b1", "b3", "c2", "c1", "c5"); Используя stream и lambda выполнить следующие действия:
//○	Удалить все содержащие “3”
//○	Отсортировать сперва по числу (по возрастанию), потом по букве (по убыванию)
//○	Отбросить первый и последний элемент
//○	Привести в uppercase
//○	Выдать на печать результат
//○	Напечатать количество оставшихся элементов

public class StreamExample {
    public static void main(String[] args) {
        List<String> myList = Arrays.asList("a1", "a2", "a3", "b1", "b3", "c2", "c1", "c5");


        var res = myList.stream().noneMatch(s->s.contains("c"));
        System.out.println(res);

        for(String s: new ArrayList<String>()){
            System.out.println(s);
        }

        // Удалить все содержащие "3"
        myList = myList.stream()
                       .filter(str -> !str.contains("3"))
                       .collect(Collectors.toList());

        // Отсортировать сперва по числу (по возрастанию), потом по букве (по убыванию)
        myList = myList.stream()
                       .sorted(Comparator.comparing((String str) -> str.substring(1, 2))
                                         .thenComparing(str -> str.substring(0, 1), Comparator.reverseOrder()))
                       .collect(Collectors.toList());

        // Отбросить первый и последний элемент
        myList = myList.stream()
                       .skip(1)
                       .limit(myList.size() - 2)
                       .collect(Collectors.toList());

        // Привести в uppercase
        myList = myList.stream()
                       .map(String::toUpperCase)
                       .collect(Collectors.toList());

        // Выдать на печать результат
        System.out.println("Результат:");
        myList.forEach(System.out::println);

        // Напечатать количество оставшихся элементов
        System.out.println("Количество оставшихся элементов: " + myList.size());
    }
}
