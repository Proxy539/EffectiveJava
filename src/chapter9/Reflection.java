package chapter9;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

public class Reflection {
    static void main(String[] args) {
        Class<? extends Set<String>> cl = null;

        try {
            cl = (Class<? extends Set<String>>)
                    Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            fatalError("Class not found");
        }

        Constructor<? extends Set<String>> cons = null;

        try {
            cons = cl.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fatalError("There is no constructor without parameters");
        }

        Set<String> s = null;

        try {
            s = cons.newInstance();
        } catch (IllegalAccessException e) {
            fatalError("Constructor is not accessible");
        } catch (InstantiationException e) {
            fatalError("Class is not instanceable");
        } catch (InvocationTargetException e) {
            fatalError("Constructor causes " + e.getCause());
        } catch (ClassCastException e) {
            fatalError("Class implements Set");
        }

        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);
    }

    private static void fatalError(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
