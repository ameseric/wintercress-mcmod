package plinth.combat;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import plinth.PlinthMod;


//@Mod.EventBusSubscriber(modid = PlinthMod.MODID ,bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityDamageHandler {

	@SubscribeEvent
	public static void _OnLivingEntityDamage( LivingDamageEvent event) {

		if( event.getEntity().getLevel().isClientSide()){
			return;
		}
		
    	System.out.println( event.getAmount());
    	
    	Entity source = event.getSource().getEntity();
    	if( source instanceof Spider) {
    		event.setAmount(0);
    		System.out.println("Negating damage from spider.");
    	}else if( source instanceof Zombie) {
    		event.setAmount(0);
    		System.out.println( "Negating damage from Zombie.");
    	}else if( source instanceof Creeper) {
    		event.setAmount(0);
    		System.out.println( "Negating damage from Creeper.");
    	}

    	System.out.println( event.getAmount());
    }
	
}
