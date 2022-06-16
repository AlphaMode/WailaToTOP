package me.alphamode.topcompact;

import com.google.common.collect.Streams;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbePlugin;
import mcp.mobius.waila.api.IPluginInfo;
import mcp.mobius.waila.api.IRegistrar;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.CustomValue;
import net.fabricmc.loader.api.metadata.ModMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.fabricmc.loader.api.metadata.CustomValue.CvType.*;

public class TOPPlugin implements ITheOneProbePlugin {
    private IRegistrar wailaRegister;

    @Override
    public void onLoad(ITheOneProbe apiInstance) {
        wailaRegister = new TOPRegistrar(apiInstance);
        collectWailaPlugins();

        for (IPluginInfo pluginInfo : PluginInfo.getAll()) {
            pluginInfo.getInitializer().register(wailaRegister);
        }
    }

    public static void collectWailaPlugins() {
        Map<ModContainer, CustomValue.CvObject[]> pluginMap = new Object2ObjectOpenHashMap<>();
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            ModMetadata data = mod.getMetadata();

            if (!data.containsCustomValue("waila:plugins"))
                continue;

            CustomValue val = data.getCustomValue("waila:plugins");
            if (val.getType() == OBJECT) {
                pluginMap.put(mod, new CustomValue.CvObject[]{val.getAsObject()});
            } else if (val.getType() == ARRAY) {
                pluginMap.put(mod, Streams.stream(val.getAsArray()).map(CustomValue::getAsObject).toArray(CustomValue.CvObject[]::new));
            } else {
                TopCompact.LOGGER.error("Plugin data provided by {} must be an object or array of objects.", data.getId());
            }
        }

        for (Map.Entry<ModContainer, CustomValue.CvObject[]> entry : pluginMap.entrySet()) {
            ModContainer mod = entry.getKey();
            CustomValue.CvObject[] plugins = entry.getValue();

            o:
            for (CustomValue.CvObject plugin : plugins) {
                List<String> requiredDeps = new ArrayList<>();
                if (plugin.containsKey("required")) {
                    CustomValue required = plugin.get("required");
                    if (required.getType() == STRING) {
                        if (FabricLoader.getInstance().isModLoaded(required.getAsString())) {
                            requiredDeps.add(required.getAsString());
                        } else {
                            continue;
                        }
                    }

                    if (required.getType() == ARRAY) {
                        for (CustomValue element : required.getAsArray()) {
                            if (element.getType() != STRING)
                                continue;

                            if (FabricLoader.getInstance().isModLoaded(element.getAsString())) {
                                requiredDeps.add(element.getAsString());
                            } else {
                                continue o;
                            }
                        }
                    }
                }

                String id = plugin.get("id").getAsString();
                String initializer = plugin.get("initializer").getAsString();

                String sideStr = plugin.containsKey("environment") ? plugin.get("environment").getAsString() : "both";
                IPluginInfo.Side side;
                switch (sideStr) {
                    case "client" -> side = IPluginInfo.Side.CLIENT;
                    case "server" -> side = IPluginInfo.Side.SERVER;
                    case "both" -> side = IPluginInfo.Side.BOTH;
                    default -> {
                        TopCompact.LOGGER.error("Environment for plugin {} is not valid, must be one of [client, server, both].", id);
                        continue o;
                    }
                }

                if (side == IPluginInfo.Side.CLIENT && FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
                    continue;
                }

                if (side == IPluginInfo.Side.SERVER && FabricLoader.getInstance().getEnvironmentType() != EnvType.SERVER) {
                    continue;
                }

                PluginInfo.register(mod.getMetadata().getId(), id, side, initializer, requiredDeps);
            }
        }
    }
}
