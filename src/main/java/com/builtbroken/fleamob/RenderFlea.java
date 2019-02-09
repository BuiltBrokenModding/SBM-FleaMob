package com.builtbroken.fleamob;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFlea extends RenderLiving<EntityFlea> {
	
	public static final ResourceLocation FLEA_TEXTURE = new ResourceLocation(FleaMobMod.MODID, "textures/flea.png");
	
	public RenderFlea(RenderManager renderManager) {
		super(renderManager, new ModelFlea(), 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFlea entity) {
		return FLEA_TEXTURE;
	}

	@Override
	protected void preRenderCallback(EntityFlea entitylivingbaseIn, float partialTickTime) {
		float scale = 0.5F;
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}
	
	

}
