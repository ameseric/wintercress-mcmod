package plinth.entities;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import plinth.PlinthMod;

public class ThiefRenderer extends HumanoidMobRenderer<ThiefEntity, ThiefModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PlinthMod.MODID, "textures/entity/thief.png");

    public ThiefRenderer(EntityRendererProvider.Context context) {
        super(context, new ThiefModel(context.bakeLayer(ThiefModel.THIEF_LAYER)), 1f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(ThiefEntity entity) {
        return TEXTURE;
    }
}
