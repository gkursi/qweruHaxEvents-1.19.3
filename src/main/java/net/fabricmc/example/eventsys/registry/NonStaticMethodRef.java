package net.fabricmc.example.eventsys.registry;

import net.fabricmc.example.Client;

import java.lang.reflect.Method;

public class NonStaticMethodRef {
    private final String methodName;
    private final Object methodParentObject;

    public static Object getObjectFromClass(Class<?> c){
         try {
             return c.getDeclaredConstructor(c).newInstance(new Object());
         } catch (Exception e) {
             return null;
         }
    }


    /*
    * NonStaticMethodRef
    * Use for quickly invoking non-static methods.
    */
    public NonStaticMethodRef(Method method, Class<?> parentClass){
        methodName = method.getName();
        methodParentObject = getObjectFromClass(parentClass);
    }
    /*
    * Invoke.
    * Invoke the assigned method.
    * */
    public void invoke(){
        try {
            Client.LOGGER.info("[NonStaticMethodRef."+methodName+"] Invoking");
            methodParentObject.getClass().getMethod(methodName).invoke(methodParentObject);
        } catch (Exception ignore){}
    }
}
