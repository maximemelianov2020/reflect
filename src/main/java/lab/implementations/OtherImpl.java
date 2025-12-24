package lab.implementations;

import lab.interfaces.SomeInterface;

/**
 * Другая реализация SomeInterface, выводящая "B".
 */
public class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.print("B");
    }
}
