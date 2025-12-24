package lab.injector;

import lab.annotation.AutoInjectable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для автоматического внедрения зависимостей в объекты
 * через механизм рефлексии и аннотации @AutoInjectable.
 *
 * @author Example
 * @version 1.0
 */
public class Injector {
    private final Properties properties;

    /**
     * Конструктор по умолчанию, загружающий конфигурацию из config.properties.
     *
     * @throws RuntimeException если файл конфигурации не найден
     */
    public Injector() {
        this.properties = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Не найден файл config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }

    /**
     * Внедряет зависимости в переданный объект.
     *
     * @param target объект для внедрения зависимостей
     * @param <T> тип объекта
     * @return объект с инициализированными зависимостями
     * @throws RuntimeException если не удалось создать экземпляр класса
     */
    public <T> T inject(T target) {
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String implementationClassName = properties.getProperty(fieldType.getName());

                if (implementationClassName == null) {
                    throw new RuntimeException(
                            "Не найдена реализация для интерфейса: " + fieldType.getName()
                    );
                }

                try {
                    Class<?> implementationClass = Class.forName(implementationClassName.trim());
                    Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();

                    field.setAccessible(true);
                    field.set(target, implementationInstance);
                } catch (Exception e) {
                    throw new RuntimeException(
                            "Ошибка создания экземпляра для поля " + field.getName(), e
                    );
                }
            }
        }
        return target;
    }
}
