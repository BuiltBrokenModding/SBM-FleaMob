package com.builtbroken.fleamob;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = FleaMobMod.MODID, name = FleaMobMod.NAME, version = FleaMobMod.VERSION, acceptedMinecraftVersions = "[1.12,1.13)")
public class FleaMobMod
{
    public static final String MODID = "sbmfleamob";
    public static final String NAME = "SBM Flea Mob";
    public static final String VERSION = "1.0.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
}
