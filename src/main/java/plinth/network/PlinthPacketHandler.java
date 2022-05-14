package plinth.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PlinthPacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
	  new ResourceLocation("plinth", "main"),
	  () -> PROTOCOL_VERSION,
	  PROTOCOL_VERSION::equals,
	  PROTOCOL_VERSION::equals
	);
}
