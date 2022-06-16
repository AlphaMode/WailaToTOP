package me.alphamode.topcompact;

import mcjty.theoneprobe.api.ITheOneProbe;
import mcp.mobius.waila.api.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TOPRegistrar implements IRegistrar {

    private final ITheOneProbe apiInstance;

    private final BlockComponentWrapper blockWrapper = new BlockComponentWrapper();

    public TOPRegistrar(ITheOneProbe apiInstance) {
        this.apiInstance = apiInstance;
        apiInstance.registerProvider(blockWrapper);
    }

    @Override
    public void addConfig(ResourceLocation key, boolean defaultValue) {

    }

    @Override
    public void addConfig(ResourceLocation key, int defaultValue, IntFormat format) {

    }

    @Override
    public void addConfig(ResourceLocation key, double defaultValue) {

    }

    @Override
    public void addConfig(ResourceLocation key, String defaultValue) {

    }

    @Override
    public <T extends Enum<T>> void addConfig(ResourceLocation key, T defaultValue) {

    }

    @Override
    public void addSyncedConfig(ResourceLocation key, boolean defaultValue, boolean clientOnlyValue) {

    }

    @Override
    public void addSyncedConfig(ResourceLocation key, int defaultValue, int clientOnlyValue, IntFormat format) {

    }

    @Override
    public void addSyncedConfig(ResourceLocation key, double defaultValue, double clientOnlyValue) {

    }

    @Override
    public void addSyncedConfig(ResourceLocation key, String defaultValue, String clientOnlyValue) {

    }

    @Override
    public <T extends Enum<T>> void addSyncedConfig(ResourceLocation key, T defaultValue, T clientOnlyValue) {

    }

    @Override
    public void addEventListener(IEventListener listener, int priority) {

    }

    @Override
    public void addBlacklist(Block... blocks) {

    }

    @Override
    public void addBlacklist(BlockEntityType<?>... blockEntityTypes) {

    }

    @Override
    public <T> void addOverride(IBlockComponentProvider provider, Class<T> clazz, int priority) {

    }

    @Override
    public <T> void addIcon(IBlockComponentProvider provider, Class<T> clazz, int priority) {

    }

    @Override
    public <T> void addComponent(IBlockComponentProvider provider, TooltipPosition position, Class<T> clazz, int priority) {
        blockWrapper.registerProvider(provider, clazz);
    }

    @Override
    public <T, BE extends BlockEntity> void addBlockData(IServerDataProvider<BE> provider, Class<T> clazz) {
        blockWrapper.registerDataProvider(provider, clazz);
    }

    @Override
    public void addBlacklist(EntityType<?>... entityTypes) {

    }

    @Override
    public <T> void addOverride(IEntityComponentProvider provider, Class<T> clazz, int priority) {

    }

    @Override
    public <T> void addIcon(IEntityComponentProvider provider, Class<T> clazz, int priority) {

    }

    @Override
    public <T> void addComponent(IEntityComponentProvider provider, TooltipPosition position, Class<T> clazz, int priority) {

    }

    @Override
    public <T, E extends Entity> void addEntityData(IServerDataProvider<E> provider, Class<T> clazz) {

    }

    @Override
    public <T> void addDisplayItem(IBlockComponentProvider provider, Class<T> clazz, int priority) {

    }

    @Override
    public void addRenderer(ResourceLocation id, ITooltipRenderer renderer) {

    }

    @Override
    public <T> void addDisplayItem(IEntityComponentProvider provider, Class<T> clazz, int priority) {

    }
}
