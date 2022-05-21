package plinth.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import plinth.PlinthMod;




public class Messages {

	private static SimpleChannel INSTANCE;
	
	private static int packetID = 0;
	private static int id() { return packetID++; }
	
	
	public static void register() {
		SimpleChannel net = NetworkRegistry.ChannelBuilder
				.named( new ResourceLocation( PlinthMod.MODID ,"messages"))
				.networkProtocolVersion(() -> "1.0")
				.clientAcceptedVersions( s -> true)
				.serverAcceptedVersions( s -> true)
				.simpleChannel();
		
		INSTANCE = net;
		
		net.messageBuilder( PacketPlayerDash.class ,id() ,NetworkDirection.PLAY_TO_SERVER)
				.decoder( PacketPlayerDash::new)
				.encoder( PacketPlayerDash::toBytes)
				.consumer( PacketPlayerDash::handle)
				.add();
		
	}
	
	public static <MSG> void sendToServer( MSG message) {
		INSTANCE.sendToServer(message);
	}
	
}
