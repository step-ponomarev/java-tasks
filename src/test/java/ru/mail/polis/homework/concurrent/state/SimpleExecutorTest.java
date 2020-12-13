package ru.mail.polis.homework.concurrent.state;

import org.junit.Assert;
import org.junit.Test;
import ru.mail.polis.homework.concurrency.executor.SimpleExecutor;

public class SimpleExecutorTest {
    private final SimpleExecutor simpleExecutorl;

    public SimpleExecutorTest() {
        simpleExecutorl = new SimpleExecutor(5);
    }

    @Test
    public void executorExecutingOnlyOneTask() {
        for (int i = 0; i < 5; i++) {
            int finalI = i;

            Runnable kek = () -> {
                System.out.println("Kek " + finalI);
            };

            simpleExecutorl.execute(kek);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Assert.assertEquals(0, simpleExecutorl.getLiveThreadsCount());
        }
    }

    @Test
    public void executorShould() {
        try {
            for (int i = 0; i < 20; i++) {
                int finalI = i;
                Runnable kek = () -> {
                    try {
                        Thread.sleep(30);
                        System.out.println("Kek1 Try: " + finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                Runnable kek2 = () -> {
                    try {
                        Thread.sleep(30);
                        System.out.println("Kek2 Try: " + finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                Runnable kek3 = () -> {
                    try {
                        Thread.sleep(30);
                        System.out.println("Kek3 Try: " + finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };

                simpleExecutorl.execute(kek);
                simpleExecutorl.execute(kek2);
                simpleExecutorl.execute(kek3);

                Assert.assertEquals(3, simpleExecutorl.getLiveThreadsCount());

                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
