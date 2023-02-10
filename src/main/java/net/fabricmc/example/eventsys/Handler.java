package net.fabricmc.example.eventsys;

import net.fabricmc.example.Client;
import net.fabricmc.example.eventsys.events.TickEvent;
import net.fabricmc.example.eventsys.registry.EventRegistry;
import net.fabricmc.example.eventsys.registry.InstanceMap;
import net.fabricmc.example.eventsys.registry.NonStaticMethodRef;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Handler {

    public final List<Class<?>> runtimeClass = new ArrayList<>();
    public final InstanceMap instanceMap = new InstanceMap();
    public final List<Class<? extends Annotation>> eventList = EventRegistry.getEvents();

    public void runAnnotationsInClass(Class<? extends Annotation> annotation, Class<?> runClass)
            throws InvocationTargetException, IllegalAccessException {
        Method[] methods = runClass.getMethods();
        for(Method mt : methods) {
            if (mt.isAnnotationPresent(annotation)) {
                instanceMap.invoke(mt.getClass(), mt.getName());
            }
        }
    }

    public void addRuntimeClass(Class<?> target){
        runtimeClass.add(target);
    }

    public void onTick() {
        // Client.LOGGER.info("(Handler.onTick) TICK");
        for(Class<?> c : runtimeClass) {
            try {
                for(Class cs : eventList){
                    runAnnotationsInClass(TickEvent.class, cs);
                }
            } catch (Exception e){
                Client.LOGGER.warn("[EVENT] Ignored class "+c.toGenericString());
                Client.LOGGER.warn("Reason: "+e.toString());
            }
        }
    }

    public void init() {
        for(Class<?> c: runtimeClass){
            Method[] methods = c.getMethods();
            for(Method mt : methods) {
                instanceMap.add(mt.getClass(), mt.getName());
            }
        }
    } // MUST BE RAN AFTER ALL MOD INITIALIZATIONS
}
