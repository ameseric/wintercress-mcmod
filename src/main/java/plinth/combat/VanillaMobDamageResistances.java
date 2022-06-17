package plinth.combat;

import java.util.HashMap;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.*;


//singleton, static-only
public class VanillaMobDamageResistances {

	
	private static HashMap<Class<?> ,DamageResistanceValues> resistances = new HashMap<>();
	
	static {
		DamageResistanceValues creeperResist = new DamageResistanceValues();
		creeperResist.setResistance( DamageType.FIRE ,50);
		resistances.put( Creeper.class ,creeperResist);
		
		DamageResistanceValues skeletonResist = new DamageResistanceValues();
		skeletonResist.setResistance( DamageType.FIRE ,50);
		skeletonResist.setResistance( DamageType.WITHER ,50);
		resistances.put( Skeleton.class ,skeletonResist);
		
		DamageResistanceValues zombieResist = new DamageResistanceValues();
		zombieResist.setResistance( DamageType.WITHER ,50);
		zombieResist.setResistance( DamageType.PHYSICAL ,50);
		resistances.put( Zombie.class ,zombieResist);
		
		DamageResistanceValues spiderResist = new DamageResistanceValues();
		resistances.put( Spider.class ,spiderResist);
		
		DamageResistanceValues enderResist = new DamageResistanceValues();
		enderResist.setResistance( DamageType.VOID ,50);
		resistances.put( EnderMan.class ,enderResist);
		
		
	}
	
	
	
	
	
	public static float getMobDamageTaken( Entity mob ,DamageType dt ,float damage) {
		float amount = damage;
		
		if( resistances.containsKey( mob.getClass())) {
			amount = resistances.get( mob.getClass()).getDamageAmount( dt ,damage);
		}
		return amount;
	}
	
	
	
}
