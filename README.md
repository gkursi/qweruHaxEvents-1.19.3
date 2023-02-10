# 1.19.3 Event system
A Basic 1.19.3 Fabric event system.

Usage:
Add ```@<EventName>Event``` to use events.
Example: ```@TickEvent```. Runs every gametick.

Keep in mind that you have to add ```Client.eventHandler.addRuntimeClass(this.class)``` at the start of every class you use events in.
You can also add that in the client onInitialise, but then you have to specify the classpath.


**Not finished**
