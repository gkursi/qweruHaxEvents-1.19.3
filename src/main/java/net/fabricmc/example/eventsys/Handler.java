package net.fabricmc.example.eventsys;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.eventsys.events.TickEvent;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Handler {

    private static final List<Class<?>> runtimeClass = new ArrayList<>();

    public static void runAnnotationsInClass(Class<? extends Annotation> annotation, Class<?> runClass)
            throws InvocationTargetException, IllegalAccessException {
        // Get all methods in order.
        // runClass is the class you declare all methods with annotations.
        Method[] methods = runClass.getMethods();
        for(Method mt : methods) {
            if (mt.isAnnotationPresent(annotation)) {
                // Invoke method with appropriate arguments

                Object obj = mt.invoke(runClass, (Object) null);
            }
        }
    }

    public static void addRuntimeClass(Class<?> target){
        runtimeClass.add(target);
    }

    // actual event defs

    public static void onTick() {
        for(Class<?> c : runtimeClass) {
            try {
                runAnnotationsInClass(TickEvent.class, c);
            } catch (Exception ignored){
                ExampleMod.LOGGER.warn("(EVENTSYS) Ignored class "+c.toGenericString());
            }
        }
    }

}
