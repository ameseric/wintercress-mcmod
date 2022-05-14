package plinth.network;

import java.nio.charset.Charset;

import com.mojang.brigadier.Message;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;
import plinth.PlinthMod;



public class PlayerDashMessage implements Message {
	
//	public PlayerDashMessage(){}
//
//	public PlayerDashMessage(String toSend) {
//	}
//
//	@Override
//	public void toBytes(ByteBuf buf) {
//	}
//
//	@Override
//	public void fromBytes(ByteBuf buf) {
//	}
//	  
//	//For client-to-server direction ONLY. RMHandler references client code.
//	public static class HandlePlayerDashMessage implements IMessageHandler< PlayerDashMessage ,IMessage> {
//
//		@Override 
//		public Message onMessage( PlayerDashMessage message ,MessageContext ctx) {	
//			EntityPlayerMP player  = ctx.getServerHandler().player;
//			WorldServer world = player.getServerWorld();
//			
//		    world.addScheduledTask(() -> {
//		    	Vec3d lastPos = TEinTE.instance.lastPlayerPos.get( player).poll();
//		    	Vec3d currentPos = player.getPositionVector();
//		    	Vec3d traj = currentPos.subtract( lastPos);
//		    	traj = new Vec3d( traj.x ,0 ,traj.z).normalize();
////		    	System.out.println( lastPos);
////		    	System.out.println( currentPos);
////		    	System.out.println( traj);
////		    	System.out.println( player.getForward());
////		    	traj = traj.normalize();
////		    	System.out.println( traj);
//		    	
//		    	if( traj.x == 0 && traj.z == 0) {
//			        Vec3d facing = player.getForward(); //this is going to break on a hosted server, need to fix
//			    	player.motionX = facing.x;
//			    	player.motionZ = facing.z;
//		    	}else {
//			    	player.motionX = traj.x;
//			    	player.motionZ = traj.z;  		
//		    	}
//		    	player.getFoodStats().addExhaustion( (float)TEinTE.config.dashHungerCost);
//	            player.connection.sendPacket(new SPacketEntityVelocity(player));
//		      });
//			
//			return null;
//		}
//	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}
}
