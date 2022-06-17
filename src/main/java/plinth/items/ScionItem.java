package plinth.items;

import java.util.UUID;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import plinth.PlinthMod;

public class ScionItem extends Item{

	public ScionItem(Properties pProperties) {
		super(pProperties);
	}
	
	
	/**vary options
	 * 
	 * projectile speed (?-?)
	 * projectile amount (1-4)
	 * projectile damage type
	 * projectile damage amount?
	 * amount of energy
	 * 
	 * 
	 * we can store item information in the ItemStack NBTCompoundTags,
	 * which carries over from Stack to Stack
	 * 
	**/
	
	
	@Override
	public InteractionResultHolder<ItemStack> use( Level level ,Player player ,InteractionHand usedHand) {
		ItemStack itemstack = player.getItemInHand(usedHand);
		
		
		if( !level.isClientSide) {
			Vec3 aim = player.getLookAngle();
			
			
//			if( !PlinthMod.instance().scionMap.containsKey(itemstack)) {
//				UUID uuid = UUID.randomUUID();
//				itemstack.setTa
//				PlinthMod.instance().scionMap.put( itemstack ,true);
//			}else {
//				Fireball fb1 = new SmallFireball( level ,player ,aim.x ,aim.y ,aim.z);
//				Vec3 pos = fb1.getPosition(0).add( aim.x ,2 ,aim.z);
//				fb1.setPos( pos);
//				level.addFreshEntity( fb1);				
//			}	
			
			
			CompoundTag scionInfo = itemstack.getOrCreateTagElement("scioninfo");
			System.out.println( scionInfo);
			if( scionInfo.isEmpty()) {
				scionInfo.putBoolean( "exists" ,true);				
			}else {
				Fireball fb1 = new SmallFireball( level ,player ,aim.x ,aim.y ,aim.z);
				Vec3 pos = fb1.getPosition(0).add( aim.x ,2 ,aim.z);
				fb1.setPos( pos);
				level.addFreshEntity( fb1);		
			}
			

			
			//player.getDirection
			//player.getForward
			Fireball fireball = new SmallFireball( level ,player ,aim.x ,aim.y ,aim.z);
			Vec3 pos = fireball.getPosition(0).add( aim.x ,1 ,aim.z);
			fireball.setPos( pos);
			level.addFreshEntity( fireball);
		}
		
		return InteractionResultHolder.pass( itemstack);
	}
	
	
	
	
	private void randomizeValues(CompoundTag scionStats) {
		//TODO
	}
	
	
	
	@Override
	public ItemStack getDefaultInstance() {
		System.out.println("==============================================================");
	      return new ItemStack(this);
   }

	
	
	

}
