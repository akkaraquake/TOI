import java.util.HashMap;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class Code {

    static double interval_start = 0; // Начало отрезка
    static double interval_end = 1; // Конец отрезка

    static HashMap<String, Double> frequency_start = new HashMap<>(); // Список левых границ  каждого символа
    static HashMap<String, Double> frequency_end = new HashMap<>(); // Список правых границ каждого символа

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Входные данные
        System.out.println("Введите строку:");
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

        // Кодирование
        System.out.println("Считанный символ\t\tЛевая граница\t\tПравая граница");
        for (int i = 0; i < text.length(); i++) {
            String s = String.valueOf(text.charAt(i));
            algorithm(interval_start, interval_end, s, frequency_start.get(s), frequency_end.get(s));
        }

    }

    private static void algorithm(double interval_start, double interval_end, String symbol, double symbol_start, double symbol_end) {

        double new_interval_start = interval_start + (interval_end - interval_start) * symbol_start;

        double new_interval_end = interval_start + (interval_end - interval_start) * symbol_end;

        Code.interval_start = new_interval_start;
        Code.interval_end = new_interval_end;

        System.out.println(symbol + "\t" + Code.interval_start + "\t\t" + Code.interval_end);
        
    }
}