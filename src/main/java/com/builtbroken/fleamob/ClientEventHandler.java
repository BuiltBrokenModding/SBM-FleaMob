package com.builtbroken.fleamob;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = FleaMobMod.MODID, value = Side.CLIENT)
public class ClientEventHandler {
	

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(final ModelRegistryEvent event) {
    	RenderingRegistry.registerEntityRenderingHandler(EntityFlea.class, RenderFlea::new);
    }
	
}
