package lab;

import lab.SomeBean;
import lab.injector.Injector;

/**
 * Демонстрационный класс для запуска примера.
 */
public class Main {
    public static void main(String[] args) {
        Injector injector = new Injector();
        SomeBean bean = injector.inject(new SomeBean());

        System.out.println("Результат выполнения:");
        bean.foo(); // Должно вывести AC или BC

        System.out.println("\nЗависимости успешно внедрены!");
    }
}
