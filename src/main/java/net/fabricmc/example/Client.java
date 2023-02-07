package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.eventsys.Handler;
import net.fabricmc.example.modules.TestModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final Handler eventHandler = new Handler();
	TestModule Example;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("(onInitialize) ---------------------------------------------------");

		Example = new TestModule();

	}


}
