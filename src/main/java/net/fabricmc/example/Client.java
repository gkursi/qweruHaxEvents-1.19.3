package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.eventsys.Handler;
import net.fabricmc.example.eventsys.events.MoveEvent;
import net.fabricmc.example.eventsys.events.TickEvent;
import net.fabricmc.example.modules.TestModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final Handler eventHandler = new Handler();
	TestModule Example;

	@Override
	public void onInitialize() {
		//
		// Defining the example module.
		// This is not a good way to create modules though,
		Example = new TestModule();

		// Registering event executors
		Client.eventHandler.addRuntimeClass(TestModule.class);

		// Registering event annotations

		eventHandler.eventList.add(TickEvent.class);


		// Initialising the event handler.
		// This has to be the last thing you initialise
		eventHandler.init();
	}


}