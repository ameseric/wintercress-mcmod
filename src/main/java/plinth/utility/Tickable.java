package plinth.utility;

import net.minecraft.world.level.Level;

public interface Tickable {

	
	public boolean isDead();
	
	
	public void _tick( int tickcount ,Level overworld);
	
	
}
