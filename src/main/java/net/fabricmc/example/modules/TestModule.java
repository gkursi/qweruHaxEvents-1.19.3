package net.fabricmc.example.modules;

import net.fabricmc.example.Client;
import net.fabricmc.example.eventsys.Handler;
import net.fabricmc.example.eventsys.events.TickEvent;

public class TestModule {
    public TestModule(){
        Client.eventHandler.addRuntimeClass(this.getClass());
    }
    @TickEvent
    public static void onTick() { // FUNCTION HAS TO BE STATIC
        Client.LOGGER.info("tick event");
    }
}
