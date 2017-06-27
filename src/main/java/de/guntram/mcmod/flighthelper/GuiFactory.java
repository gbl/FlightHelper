package de.guntram.mcmod.flighthelper;

import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class GuiFactory implements IModGuiFactory {

    @Override
    public boolean hasConfigGui() {
        return true;
    }
    
    @Override
    public void initialize(final Minecraft minecraftInstance) {
    }
    
    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new FHGuiConfig(parentScreen);
    }
    
    @Override
    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}
