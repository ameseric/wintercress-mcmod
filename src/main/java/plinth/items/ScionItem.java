package plinth.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import plinth.combat.ScionProjectile;


public class ScionItem extends Item{
	
	
	public static final int COOLDOWN = 3 * 20; 
	

	public ScionItem(Properties pProperties) {
		super(pProperties);
	}
	
	
	/**vary options
	 * 
	 * projectile speed (?-?)
	 * projectile amount (1-4)
	 * projectile damage type
	 * projectile damage amount?
	 * amount of "energy"
	 * 
	 * 
	 * we can store item information in the ItemStack NBTCompoundTags,
	 * which carries over from Stack to Stack
	 * 
	**/
	
	
	//private void setupTag(){} 
	
	
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
			if( scionInfo.isEmpty()) {
				scionInfo.putBoolean( "exists" ,true);
				scionInfo.putIntArray( "resistance", new int[]{100,0,0,0});
				scionInfo.putLong( "lastUseTime", 0);
			}else {
				Fireball fb1 = new ScionProjectile( level ,player ,aim.x ,aim.y ,aim.z);
				Vec3 pos = fb1.getPosition(0).add( aim.x ,2 ,aim.z);
				fb1.setPos( pos);
				fb1.xPower = fb1.xPower * 10;
				fb1.yPower = fb1.yPower * 10;
				fb1.zPower = fb1.zPower * 10; //works, but minecraft doesn't like fast projectiles... might be issue
				level.addFreshEntity( fb1);		
			}
			
			long lastUseTime = scionInfo.getLong( "lastUseTime");
			if( level.getGameTime() - lastUseTime > COOLDOWN ) {
			
				//player.getDirection
				//player.getForward
				Fireball fireball = new ScionProjectile( level ,player ,aim.x ,aim.y ,aim.z);
				Vec3 pos = fireball.getPosition(0).add( aim.x ,1 ,aim.z);
				fireball.setPos( pos);
				level.addFreshEntity( fireball);
				lastUseTime = level.getGameTime();
			}
			scionInfo.putLong( "lastUseTime" ,lastUseTime);
		}
		
		return InteractionResultHolder.pass( itemstack);
	}
	
	
	
	
	private void randomizeValues(CompoundTag scionStats) {
		//TODO
	}
	
	
	
	
	//issue with item reequipping when tags are updated, MUST be fixed
	//if no fix, could instead of storing cooldown, store gametime() on use, then calculate diff every tick
	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int pSlotId, boolean pIsSelected) {
	
//		if( !level.isClientSide) {
//			System.out.println( "server-side");
//		
//			CompoundTag scionInfo = stack.getOrCreateTagElement("scioninfo");
//			if( scionInfo.isEmpty()) {
//				scionInfo.putBoolean( "exists" ,true);
//				scionInfo.putIntArray( "resistance", new int[]{100,0,0,0});
//				scionInfo.putInt( "cooldown", COOLDOWN);
//			}
////			
//			int cooldown = scionInfo.getInt( "cooldown");
//			cooldown -= 1;
//			scionInfo.putInt( "cooldown" ,cooldown);
//		}
//		
		super.inventoryTick( stack ,level ,entity ,pSlotId ,pIsSelected);
	}
	
	


	
	
	

}
