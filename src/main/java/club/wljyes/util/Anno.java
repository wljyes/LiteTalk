package club.wljyes.util;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.METHOD;

public class Anno {
    @Target(METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Persons {
        Person[] value();
    }

    @Target(METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(value = Persons.class)
    @interface Person {
        String name();
    }

    @Person(name = "aaa")
    @Person(name = "bbb")
    @Person(name = "ccc")
    public void work() throws NoSuchMethodException {
        Person[] people = this.getClass().getMethod("work").getAnnotationsByType(Person.class);
        for (Person person : people) {
            System.out.println(person.name());
        }
    }

    public static void main(String[] args) {
        try {
            new Anno().work();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
