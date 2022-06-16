package me.alphamode.topcompact;

import com.google.common.base.Preconditions;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.component.PairComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockComponentWrapper<T> implements IProbeInfoProvider {
    private final Map<Class<T>, IBlockComponentProvider> providers = new HashMap<>();
    private final Map<Class<BlockEntity>, IServerDataProvider<BlockEntity>> dataProviders = new HashMap<>();
    private static final Tooltip tooltip = new Tooltip();

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation("topcompat", "top_block_wrapper");
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
        BlockEntity blockEntity = world.getBlockEntity(data.getPos());
        IServerDataProvider<BlockEntity> serverDataProvider;
        BlockHitResult hitResult = new BlockHitResult(data.getHitVec(), data.getSideHit(), data.getPos(), false);
        IServerAccessor serverAccessor = new ServerAccessor(world, (ServerPlayer) player, hitResult, blockEntity);
        CompoundTag tag;
        if (blockEntity != null) {
            tag = blockEntity.saveWithFullMetadata();
            serverDataProvider = dataProviders.get(blockEntity.getClass());
        } else {
            tag = new CompoundTag();
            serverDataProvider = null;
        }
        if (serverDataProvider != null) {
            serverDataProvider.appendServerData(tag, serverAccessor, null);
            serverDataProvider.appendServerData(tag, (ServerPlayer) player, world, blockEntity);
        }
        IBlockComponentProvider provider = providers.get(blockState.getBlock().getClass());
        if (provider != null) {
            ProbeBlockAccessor probeBlockAccessor = new ProbeBlockAccessor(data, world, player, blockState, hitResult, tag);

            provider.appendHead((ITooltip) tooltip, probeBlockAccessor, null);
            provider.appendHead((List<Component>) tooltip, probeBlockAccessor, null);
            drawTooltip(probeInfo);
            provider.appendBody((ITooltip) tooltip, probeBlockAccessor, null);
            provider.appendBody((List<Component>) tooltip, probeBlockAccessor, null);
            drawTooltip(probeInfo);
            provider.appendTail((ITooltip) tooltip, probeBlockAccessor, null);
            provider.appendTail((List<Component>) tooltip, probeBlockAccessor, null);
            drawTooltip(probeInfo);
        }
    }

    public void drawTooltip(IProbeInfo probeInfo) {
        for (Component component : tooltip) {
            probeInfo.text(component);
        }
        tooltip.clear();
    }

    public void registerProvider(IBlockComponentProvider provider, Class<T> clazz) {
        providers.put(clazz, provider);
    }

    public void registerDataProvider(IServerDataProvider<BlockEntity> provider, Class<BlockEntity> clazz) {
        dataProviders.put(clazz, provider);
    }

}
