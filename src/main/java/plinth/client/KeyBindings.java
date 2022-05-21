package plinth.client;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBindings {

	public static final String KEY_CATEGORIES_PLINTH = "key.categories.plinth";
	
	public static final String KEY_DASH = "key.dash";
	
	public static KeyMapping playerDashKeyMapping;
	
	
	public static void init() {
		playerDashKeyMapping = new KeyMapping( 
						KEY_DASH 
						,KeyConflictContext.IN_GAME 
						,InputConstants.getKey( "key.keyboard.period") 
						,KEY_CATEGORIES_PLINTH);
		ClientRegistry.registerKeyBinding( playerDashKeyMapping);
	}
	
	
}
