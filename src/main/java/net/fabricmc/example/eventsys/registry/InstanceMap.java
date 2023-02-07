package net.fabricmc.example.eventsys.registry;

import net.fabricmc.example.Client;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InstanceMap {
    private final List<Object> objects = new ArrayList<>();
    private final List<String> methods = new ArrayList<>();

    public void add(Object object, String method){
        Method m;
        try {
            Method meth = object.getClass().getMethod(method);
            objects.add(object);
            methods.add(method);
        } catch (Exception ignore){}
    }

    public void invoke(Class<?> invokeParent, String invokable){
        boolean fail = true;
        for(Object o : objects){
            if(o.getClass()==invokeParent){
                try {
                    o.getClass().getMethod(invokable).invoke(o);
                    fail = false;
                    break;
                } catch (Exception e){
                    Client.LOGGER.error("[EVENT] Unable to invoke method "+invokable);
                    Client.LOGGER.error("Reason: "+e.toString());
                }
            }
        }

        if(fail){
            try{
                NonStaticMethodRef methodRef = new NonStaticMethodRef(
                                invokeParent.getMethod(invokable),
                                invokeParent
                        );
                methodRef.invoke();
            } catch (Exception e){
                Client.LOGGER.error("[EVENT] Unable to invoke method "+invokable);
                Client.LOGGER.error("Reason: "+e.toString());
            }
        }

    }

    public boolean contains(Object object, String method){
        for(Object o : objects){
            if(!(object==o)) continue;
            for(String m : methods){
                if(Objects.equals(m, method)) return true;
            }
        }

        return false;
    }

    public boolean contains(Class<?> c, String method){
        Object cl = NonStaticMethodRef.getObjectFromClass(c);
        for(Object o : objects){
            if(!(cl==o)) continue;
            for(String m : methods){
                if(Objects.equals(m, method)) return true;
            }
        }

        return false;
    }


}
