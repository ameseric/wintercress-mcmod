package plinth.network;

import java.util.function.Supplier;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import plinth.PlinthMod;




public class PacketPlayerDash {

	public PacketPlayerDash() {		
	}
	
	public PacketPlayerDash( FriendlyByteBuf buf) {
		
	}
	
	public void toBytes(FriendlyByteBuf buf) {
		
	}
	
	public boolean handle( Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context ctx = supplier.get();
		
		ctx.enqueueWork(() -> {
			ServerPlayer player = ctx.getSender();
			
			//take note, way to send messages in chat
			player.sendMessage( new TranslatableComponent( "Working?").withStyle(ChatFormatting.BLUE) ,Util.NIL_UUID);
			
			
//	    	Vec3 lastPos = player.position();//TEinTE.instance.lastPlayerPos.get( player).poll();
//	    	Vec3 currentPos = player.getPositionVector();
//	    	Vec3 traj = currentPos.subtract( lastPos);
//	    	traj = new Vec3( traj.x ,0 ,traj.z).normalize();
//			Vec3 traj = new Vec3( 0.5 ,0 ,0.5);
//			boolean playerIsMoving = player.getDeltaMovement().x > 0 && player.getDeltaMovement().z > 0;
//			if( playerIsMoving) {
//				player.setDeltaMovement(traj);
//			}
			
			Vec3 previous = PlinthMod.instance().lastPlayerPos.get( player).poll();
			Vec3 current = player.getPosition(1);
			Vec3 traj = current.subtract( previous);
			traj = new Vec3( traj.x ,0 ,traj.z).normalize();
			Vec3 move = new Vec3(0,0,0);
			
//    	System.out.println( "====================");
//		System.out.println( previous);
//    	System.out.println( current);
//    	System.out.println( traj);
//    	System.out.println( player.getForward());
//    	traj = traj.normalize();
//    	System.out.println( traj);
			
			
	    	if( traj.x == 0 && traj.z == 0) {
	    		Vec3 facing = player.getForward(); //this is going to break on a hosted server, need to fix
	    		move = move.add( facing.x ,0 ,facing.z);
	    	}else {
	    		move = move.add( traj.x ,0 ,traj.z);
    		}
			
	    	player.getFoodData().addExhaustion( 1); //evaluate
	    	
//	    	move = new Vec3( 0.5 ,0.1 ,0.5);
	    	player.setDeltaMovement( move);
	    	
//	    	player.knockback( 1.0 ,0.5 ,0.5);

	    	player.connection.send( new ClientboundSetEntityMotionPacket( player));
			
		});
		
		return true;
	}
	
}
