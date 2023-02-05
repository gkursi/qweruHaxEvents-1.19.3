package net.fabricmc.example.modules;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.eventsys.Handler;
import net.fabricmc.example.eventsys.UseEvent;
import net.fabricmc.example.eventsys.events.MoveEvent;
import net.fabricmc.example.eventsys.events.TickEvent;

public class example {
    public example(){
        Handler.addRuntimeClass(this.getClass());
    }
    @TickEvent
    public void onTick() {
        ExampleMod.LOGGER.info("----------------- EXECUTED TICK EVENT ------------------");
    }
}
