package lab;

import lab.annotation.AutoInjectable;
import lab.interfaces.SomeInterface;
import lab.interfaces.SomeOtherInterface;

/**
 * Пример класса с полями, помеченными аннотацией @AutoInjectable.
 * Эти поля должны быть автоматически инициализированы Injector'ом.
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Метод, использующий внедренные зависимости.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}
