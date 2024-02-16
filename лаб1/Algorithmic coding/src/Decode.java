import java.util.HashMap;
import java.util.Scanner;

public class Decode {

    static double symbol_start; // Левая граница рассматриваемого символа
    static double symbol_end; // Правая граница рассматриваемого символа

    static HashMap<String, Double> frequency_start = new HashMap<>(); // Список левых границ  каждого символа
    static HashMap<String, Double> frequency_end = new HashMap<>(); // Список правых границ каждого символа

    static String result_text = ""; // Строка, которую нужно получить

    static Double code; // Результат кодирования

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Входные данные
        System.out.println("Введите строку, которую нужно получить:");
        String text = input.nextLine();

        System.out.println("Введите символ и его частоту через пробел:");

        int numb_of_iteration = 0;
        double total_frequency = 0;

        while (true) {

            String symbol = input.next();
            if (symbol.equals("end")) break;

            Double symbol_frequency = input.nextDouble();

            if (numb_of_iteration == 0) {
                frequency_start.put(symbol, 0.0);
                frequency_end.put(symbol, symbol_frequency);
            }
            else {
                frequency_start.put(symbol, total_frequency);
                frequency_end.put(symbol, total_frequency + symbol_frequency < 1 ? total_frequency + symbol_frequency : 1);
            }

            numb_of_iteration += 1;
            total_frequency += symbol_frequency;
        }

        System.out.println("Введите результат кодирования:");
        code = input.nextDouble();

        // Декодирование
        System.out.println("Декодируемый символ\t\tКод");
        while (!text.equals(result_text)) {

            for (String symbol : frequency_start.keySet()) {
                symbol_start = frequency_start.get(symbol);
                symbol_end = frequency_end.get(symbol);

                if (code >= symbol_start && code <= symbol_end) {
                    result_text += symbol;
                    System.out.println(symbol + "\t" + code);
                    break;
                }
            }
            algorithm(symbol_start, symbol_end);
        }

    }

    private static void algorithm(double symbol_start, double symbol_end) {

        code = (code - symbol_start) / (symbol_end - symbol_start);
    }
}
