package plinth.combat;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;



//@Mod.EventBusSubscriber(modid = PlinthMod.MODID ,bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityDamageHandler {

	@SubscribeEvent
	public static void _OnLivingEntityDamage( LivingDamageEvent event) {

		Entity source = event.getSource().getEntity();
		Entity target = event.getEntity();
		
		if( target.getLevel().isClientSide()){
			return;
		}
		
    	if( source != null) {
	    	System.out.println( source.getClass().getCanonicalName() + " is attacking " + target.getClass().getCanonicalName());
			System.out.println( "Damage before adjustment: " + event.getAmount());
			
	    	
	    	if( target instanceof Player) {
	    		//get source damage type (if possible)
	    		//check player resistances
	    		
	    	}else if( source instanceof Player) {
	    		//get player weapon damage type
	    		event.setAmount( VanillaMobDamageResistances.getMobDamageTaken( target ,DamageType.FIRE ,event.getAmount()));
	    	}
	
	    	System.out.println( "Damage after adjustment: " + event.getAmount());
    	}
    }	
}
