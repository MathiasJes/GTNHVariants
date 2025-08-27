package com.gtnh.gtnhvariants.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.WorldEvent;

import com.gtnh.gtnhvariants.GTNHVariants;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCreateWorldExtension {

    private static final int BTN_GT_OPTIONS = 91;

    private static final int CANCEL_BUTTON_ID = 1;
    private static final int MORE_WORLD_OPTIONS_BUTTON_ID = 3;
    private static final int BONUS_CHEST_BUTTON_ID = 7;

    private GuiButton variantButton = null;
    private GuiButton anchor = null;

    @SubscribeEvent
    public void onInit(GuiScreenEvent.InitGuiEvent.Post e) {
        if (!(e.gui instanceof GuiCreateWorld)) return;

        GTNHGuiWorldConfig.clear();
        for (Object button : e.buttonList) {
            if (GuiButton.class.cast(button).id == BONUS_CHEST_BUTTON_ID) {
                anchor = GuiButton.class.cast(button);
            }
        }

        int x, y, w, h;

        x = anchor.xPosition;
        y = anchor.yPosition;
        w = anchor.width;
        h = anchor.height;

        variantButton = new GuiButton(BTN_GT_OPTIONS, x, y, w, h, GTNHGuiWorldConfig.getCurrentOptionName());
        variantButton.visible = false;
        e.buttonList.add(variantButton);
    }

    @SubscribeEvent
    public void onDrawScreen(GuiScreenEvent.DrawScreenEvent.Post e) {
        if (e.gui instanceof GuiCreateWorld && variantButton.visible) {
            GuiCreateWorld gui = GuiCreateWorld.class.cast(e.gui);
            gui.drawString(
                gui.mc.fontRenderer,
                "GTNH Variant:",
                variantButton.xPosition + 5,
                variantButton.yPosition - 13,
                -6250336);

            gui.drawString(
                gui.mc.fontRenderer,
                GTNHGuiWorldConfig.getCurrentOptionDescription(),
                variantButton.xPosition + 5,
                variantButton.yPosition + variantButton.height + 1,
                -6250336);
        }
    }

    @SubscribeEvent
    public void onAction(GuiScreenEvent.ActionPerformedEvent.Post e) {
        if (!(e.gui instanceof GuiCreateWorld)) return;

        if (e.button != null && e.button.id == MORE_WORLD_OPTIONS_BUTTON_ID) {
            variantButton.visible = !variantButton.visible;
            anchor.visible = false;
            anchor.enabled = false;
        }

        if (e.button != null && e.button == variantButton) {
            GTNHGuiWorldConfig.cycleWorldOptions();
            variantButton.displayString = GTNHGuiWorldConfig.getCurrentOptionName();
        }

        if (e.button != null && e.button.id == CANCEL_BUTTON_ID) {
            GTNHGuiWorldConfig.clear();
        }
    }
}
