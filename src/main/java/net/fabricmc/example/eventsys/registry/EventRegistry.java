package net.fabricmc.example.eventsys.registry;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class EventRegistry {
    private static final List<Class<? extends Annotation>> annotations = new ArrayList<>();

    public static void addEvent(Class<? extends Annotation> annotation){
        annotations.add(annotation);
    }

    public static List<Class<? extends Annotation>> getEvents(){
        return annotations;
    }
}
