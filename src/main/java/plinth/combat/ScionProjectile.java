package plinth.combat;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;


//   public static final EntityType<SmallFireball> SMALL_FIREBALL = register("small_fireball", EntityType.Builder.<SmallFireball>of(SmallFireball::new, MobCategory.MISC).sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10));


public class ScionProjectile extends Fireball {
	
	
	public DamageType damagetype = DamageType.PHYSICAL;
	
	
	   public ScionProjectile(EntityType<? extends SmallFireball> p_37364_, Level p_37365_) {
		      super(p_37364_, p_37365_);
	   }

	   public ScionProjectile(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
	      super(EntityType.SMALL_FIREBALL, p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	   }

	   public ScionProjectile(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_, double p_37372_, double p_37373_) {
	      super(EntityType.SMALL_FIREBALL, p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	   }
	   
	   
	   
	   
	   /**
	    * Called when the arrow hits an entity
	    */
	   protected void onHitEntity(EntityHitResult pResult) {
	      super.onHitEntity(pResult);
	      if (!this.level.isClientSide) {
	         Entity entity = pResult.getEntity();
	         Entity entity1 = this.getOwner();
	         entity.hurt(DamageSource.fireball(this, entity1), 6.0F);
	         if (entity1 instanceof LivingEntity) {
	            this.doEnchantDamageEffects((LivingEntity)entity1, entity);
	         }

	      }
	   }



	   /**
	    * Called when this EntityFireball hits a block or entity.
	    */
	   protected void onHit(HitResult pResult) {
		  System.out.println( "XPOWER: " + this.xPower);
	      super.onHit(pResult);
	      if (!this.level.isClientSide) {
	         this.discard();
	      }

	   }
	   

	   /**
	    * Returns true if other Entities should be prevented from moving through this Entity.
	    */
	   public boolean isPickable() {
	      return false;
	   }

	   /**
	    * Called when the entity is attacked.
	    */
	   public boolean hurt(DamageSource pSource, float pAmount) {
	      return false;
	   }
}
