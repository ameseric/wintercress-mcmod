package plinth.client;

import net.minecraftforge.client.event.InputEvent;
import plinth.network.Messages;
import plinth.network.PacketPlayerDash;

public class KeyInputHandler {

	public static void onKeyInput( InputEvent.KeyInputEvent event) {
		if( KeyBindings.playerDashKeyMapping.consumeClick()) {
			Messages.sendToServer( new PacketPlayerDash());
		}
	}
	
}
