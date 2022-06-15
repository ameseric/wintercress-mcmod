package plinth.items;

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

public class ScionItem extends Item{

	public ScionItem(Properties pProperties) {
		super(pProperties);
	}
	
	
	
	
	@Override
	public InteractionResultHolder<ItemStack> use( Level level ,Player player ,InteractionHand usedHand) {
		
		if( !level.isClientSide) {
			Vec3 aim = player.getLookAngle();
			//player.getDirection
			//player.getForward
			Fireball fireball = new SmallFireball( level ,player ,aim.x ,aim.y ,aim.z);
			Vec3 pos = fireball.getPosition(0).add( aim.x ,1 ,aim.z);
			fireball.setPos( pos);
			level.addFreshEntity( fireball);
		}
		
		return InteractionResultHolder.pass( player.getItemInHand(usedHand));
	}

	
	
	

}
