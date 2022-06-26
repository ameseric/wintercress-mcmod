package plinth.setup;

import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import plinth.ObjectCatalog;
import plinth.PlinthMod;
import plinth.entities.ThiefEntity;

//@Mod.EventBusSubscriber(modid = PlinthMod.MODID ,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    @SubscribeEvent //this event only works on MOD bus, NOT FORGE
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put( ObjectCatalog.THIEF.get(), ThiefEntity.createAttributes().build());
    }

    
}