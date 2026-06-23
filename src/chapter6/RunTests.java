package chapter6;

import java.awt.event.TextEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

public class RunTests {

    static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;

        Class<?> testClass = Class.forName(args[0]);
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest.class)
            || m.isAnnotationPresent(ExceptionTestContainer.class)) {
               tests++;

               try {
                   m.invoke(null);
                   System.out.printf("Test fail %s: no exception %n", m);
               } catch (Throwable wrappedExc) {
                   Throwable exc = wrappedExc.getCause();
                   int oldPassed = passed;
                   ExceptionTest[] excTests =
                           m.getAnnotationsByType(ExceptionTest.class);

                   for (ExceptionTest excTest : excTests) {
                       if (excTest.value().isInstance(exc)) {
                           passed++;
                           break;
                       }
                   }

                   if (passed == oldPassed) {
                       System.out.printf("Test fail %s: %s %n", m, exc);
                   }
               }
            }
        }

        System.out.printf("Passed: %d, failed: %d%n", passed, tests - passed);
    }
}
