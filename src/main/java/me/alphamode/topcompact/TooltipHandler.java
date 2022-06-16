package me.alphamode.topcompact;

import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import com.mojang.text2speech.Narrator;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import mcp.mobius.waila.api.ITooltipComponent;
import mcp.mobius.waila.api.component.EmptyComponent;
import mcp.mobius.waila.api.component.PairComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.function.Supplier;

public class TooltipHandler extends GuiComponent {

    private static final Tooltip TOOLTIP = new Tooltip();
    private static final Object2IntOpenHashMap<Line> LINE_HEIGHT = new Object2IntOpenHashMap<>();

    private static final Supplier<Rectangle> RENDER_RECT = Suppliers.memoize(Rectangle::new);
    private static final Supplier<Rectangle> RECT = Suppliers.memoize(Rectangle::new);
    private static final Supplier<Narrator> NARRATOR = Suppliers.memoize(Narrator::getNarrator);

    static boolean shouldRender = false;

    private static String lastNarration = "";
    private static ITooltipComponent icon = EmptyComponent.INSTANCE;
    private static int topOffset;

    public static int colonOffset;
    public static int colonWidth;

    private static boolean started = false;

    public static void beginBuild() {
        TOOLTIP.clear();
        LINE_HEIGHT.clear();
        icon = EmptyComponent.INSTANCE;
        topOffset = 0;
        colonOffset = 0;
        colonWidth = Minecraft.getInstance().font.width(": ");
        started = true;
    }



}
