package lab.implementations;

import lab.interfaces.SomeInterface;

/**
 * Реализация SomeInterface, выводящая "A".
 */
public class SomeImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.print("A");
    }
}
