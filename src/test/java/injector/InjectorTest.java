package injector;

import lab.annotation.AutoInjectable;
import lab.injector.Injector;
import lab.SomeBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InjectorTest {

    // Тест проверяет, что инъекция зависимостей работает без ошибок
    @Test
    public void testInjection() throws Exception {

        // Используем конструктор по умолчанию
        Injector injector = new Injector();

        // Создаём и инжектим зависимости в SomeBean
        SomeBean sb = injector.inject(new SomeBean());

        // Проверяем, что объект был создан (не null)
        assertNotNull(sb, "SomeBean object should not be null after injection");

        // Вызываем метод foo() для проверки, что зависимости работают
        // Если зависимости не были инжектированы, здесь будет NullPointerException
        sb.foo();
    }
}
