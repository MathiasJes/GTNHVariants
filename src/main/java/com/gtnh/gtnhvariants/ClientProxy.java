package com.gtnh.gtnhvariants;

import net.minecraftforge.common.MinecraftForge;

import com.gtnh.gtnhvariants.gui.GuiCreateWorldExtension;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        // Register our GUI event handler on the client bus
        MinecraftForge.EVENT_BUS.register(new GuiCreateWorldExtension());
    }

}
