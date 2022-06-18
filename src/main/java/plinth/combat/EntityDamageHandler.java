package plinth.combat;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
	    		Inventory inv = ((Player) target).getInventory();
	    		DamageResistanceValues playerDMV = new DamageResistanceValues();
	    		CompoundTag tag;
	    		for( ItemStack is : inv.items) {
	    			if( !is.isEmpty()) {
		    			tag = is.getTagElement( "scioninfo");
		    			if( tag != null) {
		    				System.out.println( "Scion found");
		    				System.out.println( is.toString());
		    				playerDMV.addResistances( tag.getIntArray( "resistance"));
		    			}
	    			}
	    		}
	    		System.out.println( playerDMV);
	    		event.setAmount( playerDMV.getDamageAmount( DamageType.PHYSICAL ,event.getAmount()));
	    		
	    	}else if( source instanceof Player) {
	    		//get player weapon damage type
	    		Entity projectile = event.getSource().getDirectEntity();
	    		DamageType dt = DamageType.WITHER;
	    		if( projectile != null) {
	    			System.out.println( "Projectile used: " + projectile.getClass().getCanonicalName());
	    			if( projectile instanceof ScionProjectile) {
	    				dt = ((ScionProjectile) projectile).damagetype;
	    			}
	    		}
	    		event.setAmount( VanillaMobDamageResistances.getMobDamageTaken( target ,dt ,event.getAmount()));
	    	}
	
	    	System.out.println( "Damage after adjustment: " + event.getAmount());
    	}
    }	
}
