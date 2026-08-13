import java.util.*;
import java.util.stream.Collectors;

//●	Дана коллекция Collection<String> col. С помощью stream api:
//○	узнать, содержит ли какая-нибудь из строк слово login
//○	найти самую длинную строку
//○	найти самую короткую строку
//○	найти строки-слова (не содержат пробелов и знаков препинания)
//○	получить все слова используемые в строках

public class StringCollectionOperations {

    public static void main(String[] args) {
        Collection<String> col = Arrays.asList("This is a test", "login", "Hello World", "Short", "Another test", "Java Stream API");

        // Узнать, содержит ли какая-нибудь из строк слово "login"
        boolean containsLogin = col.stream()
                .anyMatch(str -> str.contains("login"));
        System.out.println("Содержит ли какая-нибудь из строк слово 'login': " + containsLogin);

        // Найти самую длинную строку
        Optional<String> longestString = col.stream()
                .max(Comparator.comparingInt(String::length));
        longestString.ifPresent(str -> System.out.println("Самая длинная строка: " + str));

        // Найти самую короткую строку
        Optional<String> shortestString = col.stream()
                .min(Comparator.comparingInt(String::length));
        shortestString.ifPresent(str -> System.out.println("Самая короткая строка: " + str));

        // Найти строки-слова (не содержат пробелов и знаков препинания)
        List<String> wordStrings = col.stream()
                .filter(str -> str.matches("\\b\\w+\\b"))
                .collect(Collectors.toList());
        System.out.println("Строки-слова: " + wordStrings);

        // Получить все слова используемые в строках
        Set<String> allWords = col.stream()
                .flatMap(str -> Arrays.stream(str.split("\\s+")))
                .collect(Collectors.toSet());
        System.out.println("Все слова используемые в строках: " + allWords);
    }
}

