package mcp.mobius.waila.api;

import mcp.mobius.waila.api.__internal__.ApiSide;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface IRegistrar {

    /**
     * The default priority for all component.
     */
    int DEFAULT_PRIORITY = 1000;

    /**
     * Registers a namespaced config key to be accessed within data providers.
     *
     * @param key          the namespaced key
     * @param defaultValue the default value
     */
    void addConfig(ResourceLocation key, boolean defaultValue);

    /**
     * Registers a namespaced config key to be accessed within data providers.
     *
     * @param key          the namespaced key
     * @param defaultValue the default value
     * @param format       used for formatting text box in plugin config screen
     */
    void addConfig(ResourceLocation key, int defaultValue, IntFormat format);

    /**
     * Registers a namespaced config key to be accessed within data providers.
     *
     * @param key          the namespaced key
     * @param defaultValue the default value
     */
    default void addConfig(ResourceLocation key, int defaultValue) {
        addConfig(key, defaultValue, IntFormat.DECIMAL);
    }

    /**
     * Registers a namespaced config key to be accessed within data providers.
     *
     * @param key          the namespaced key
     * @param defaultValue the default value
     */
    void addConfig(ResourceLocation key, double defaultValue);

    /**
     * Registers a namespaced config key to be accessed within data providers.
     *
     * @param key          the namespaced key
     * @param defaultValue the default value
     */
    void addConfig(ResourceLocation key, String defaultValue);

    /**
     * Registers a namespaced config key to be accessed within data providers.
     *
     * @param key          the namespaced key
     * @param defaultValue the default value
     */
    <T extends Enum<T>> void addConfig(ResourceLocation key, T defaultValue);

    /**
     * Registers a namespaced config key to be accessed within data providers.
     * These values are sent from the server to the client upon connection.
     *
     * @param key             The namespaced key
     * @param defaultValue    The default value
     * @param clientOnlyValue The value that will be used when the server connected doesn't have waila installed
     */
    void addSyncedConfig(ResourceLocation key, boolean defaultValue, boolean clientOnlyValue);


    /**
     * Registers a namespaced config key to be accessed within data providers.
     * These values are sent from the server to the client upon connection.
     *
     * @param key             The namespaced key
     * @param defaultValue    The default value
     * @param clientOnlyValue The value that will be used when the server connected doesn't have waila installed
     * @param format          used for formatting text box in plugin config screen
     */
    void addSyncedConfig(ResourceLocation key, int defaultValue, int clientOnlyValue, IntFormat format);

    /**
     * Registers a namespaced config key to be accessed within data providers.
     * These values are sent from the server to the client upon connection.
     *
     * @param key             The namespaced key
     * @param defaultValue    The default value
     * @param clientOnlyValue The value that will be used when the server connected doesn't have waila installed
     */
    default void addSyncedConfig(ResourceLocation key, int defaultValue, int clientOnlyValue) {
        addSyncedConfig(key, defaultValue, clientOnlyValue, IntFormat.DECIMAL);
    }

    /**
     * Registers a namespaced config key to be accessed within data providers.
     * These values are sent from the server to the client upon connection.
     *
     * @param key             The namespaced key
     * @param defaultValue    The default value
     * @param clientOnlyValue The value that will be used when the server connected doesn't have waila installed
     */
    void addSyncedConfig(ResourceLocation key, double defaultValue, double clientOnlyValue);

    /**
     * Registers a namespaced config key to be accessed within data providers.
     * These values are sent from the server to the client upon connection.
     *
     * @param key             The namespaced key
     * @param defaultValue    The default value
     * @param clientOnlyValue The value that will be used when the server connected doesn't have waila installed
     */
    void addSyncedConfig(ResourceLocation key, String defaultValue, String clientOnlyValue);

    /**
     * Registers a namespaced config key to be accessed within data providers.
     * These values are sent from the server to the client upon connection.
     *
     * @param key             The namespaced key
     * @param defaultValue    The default value
     * @param clientOnlyValue The value that will be used when the server connected doesn't have waila installed
     */
    <T extends Enum<T>> void addSyncedConfig(ResourceLocation key, T defaultValue, T clientOnlyValue);

    /**
     * Adds an event listener
     */
    void addEventListener(IEventListener listener, int priority);

    /**
     * Adds an event listener
     */
    default void addEventListener(IEventListener listener) {
        addEventListener(listener, DEFAULT_PRIORITY);
    }

    /**
     * Adds the specified entity types to the default blacklist.
     */
    void addBlacklist(Block... blocks);

    /**
     * Adds the specified entity types to the default blacklist.
     */
    void addBlacklist(BlockEntityType<?>... blockEntityTypes);

    /**
     * Registers an {@link IBlockComponentProvider} instance to allow overriding the block being displayed.
     * A {@link BlockEntity} is also an acceptable class type.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     * @param priority the priority of this provider <b>0 is the minimum, lower number will be called first</b>
     *
     * @see #DEFAULT_PRIORITY
     */
    @ApiSide.ClientOnly
    <T> void addOverride(IBlockComponentProvider provider, Class<T> clazz, int priority);

    /**
     * Registers an {@link IBlockComponentProvider} instance with {@link #DEFAULT_PRIORITY} to allow overriding the block being displayed.
     * A {@link BlockEntity} is also an acceptable class type.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     *
     * @see #DEFAULT_PRIORITY
     */
    @ApiSide.ClientOnly
    default <T> void addOverride(IBlockComponentProvider provider, Class<T> clazz) {
        addOverride(provider, clazz, DEFAULT_PRIORITY);
    }

    /**
     * Registers an {@link IBlockComponentProvider} instance to allow overriding the displayed icon for a block via the
     * {@link IBlockComponentProvider#getIcon(IBlockAccessor, IPluginConfig)} method.
     * A {@link BlockEntity} is also an acceptable class type.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     * @param priority the priority of this provider <b>0 is the minimum, lower number will be called first</b>
     *
     * @see #DEFAULT_PRIORITY
     */
    @ApiSide.ClientOnly
    <T> void addIcon(IBlockComponentProvider provider, Class<T> clazz, int priority);

    /**
     * Registers an {@link IBlockComponentProvider} instance with {@link #DEFAULT_PRIORITY} to allow overriding the displayed icon for a block via the
     * {@link IBlockComponentProvider#getIcon(IBlockAccessor, IPluginConfig)} method.
     * A {@link BlockEntity} is also an acceptable class type.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     */
    @ApiSide.ClientOnly
    default <T> void addIcon(IBlockComponentProvider provider, Class<T> clazz) {
        addIcon(provider, clazz, DEFAULT_PRIORITY);
    }

    /**
     * Registers an {@link IBlockComponentProvider} instance for appending {@link Component} to the tooltip.
     * A {@link BlockEntity} is also an acceptable class type.
     *
     * @param provider the data provider instance
     * @param position the position on the tooltip this applies to
     * @param clazz    the highest level class to apply to
     * @param priority the priority of this provider <b>0 is the minimum, lower number will be called first</b>
     *
     * @see #DEFAULT_PRIORITY
     */
    @ApiSide.ClientOnly
    <T> void addComponent(IBlockComponentProvider provider, TooltipPosition position, Class<T> clazz, int priority);

    /**
     * Registers an {@link IBlockComponentProvider} instance with {@link #DEFAULT_PRIORITY} for appending {@link Component} to the tooltip.
     * A {@link BlockEntity} is also an acceptable class type.
     *
     * @param provider the data provider instance
     * @param position the position on the tooltip this applies to
     * @param clazz    the highest level class to apply to
     */
    @ApiSide.ClientOnly
    default <T> void addComponent(IBlockComponentProvider provider, TooltipPosition position, Class<T> clazz) {
        addComponent(provider, position, clazz, DEFAULT_PRIORITY);
    }

    /**
     * Registers an {@link IServerDataProvider<BlockEntity>} instance for data syncing purposes. A {@link BlockEntity}
     * is also an acceptable class type.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     */
    @ApiSide.ServerOnly
    <T, BE extends BlockEntity> void addBlockData(IServerDataProvider<BE> provider, Class<T> clazz);

    /**
     * Adds the specified entity types to the default blacklist.
     */
    void addBlacklist(EntityType<?>... entityTypes);

    /**
     * Registers an {@link IEntityComponentProvider} instance to allow overriding the entity being displayed.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     * @param priority the priority of this provider <b>0 is the minimum, lower number will be called first</b>
     *
     * @see #DEFAULT_PRIORITY
     */
    @ApiSide.ClientOnly
    <T> void addOverride(IEntityComponentProvider provider, Class<T> clazz, int priority);

    /**
     * Registers an {@link IEntityComponentProvider} instance with {@link #DEFAULT_PRIORITY} to allow overriding the entity being displayed.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     *
     * @see #DEFAULT_PRIORITY
     */
    @ApiSide.ClientOnly
    default <T> void addOverride(IEntityComponentProvider provider, Class<T> clazz) {
        addOverride(provider, clazz, DEFAULT_PRIORITY);
    }

    /**
     * Registers an {@link IEntityComponentProvider} instance to allow displaying an icon via the
     * {@link IEntityComponentProvider#getIcon(IEntityAccessor, IPluginConfig)} method.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     * @param priority the priority of this provider <b>0 is the minimum, lower number will be called first</b>
     *
     * @see #DEFAULT_PRIORITY
     */
    <T> void addIcon(IEntityComponentProvider provider, Class<T> clazz, int priority);

    /**
     * Registers an {@link IEntityComponentProvider} instance with {@link #DEFAULT_PRIORITY} to allow displaying an icon via the
     * {@link IEntityComponentProvider#getIcon(IEntityAccessor, IPluginConfig)} method.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     */
    default <T> void addIcon(IEntityComponentProvider provider, Class<T> clazz) {
        addIcon(provider, clazz, DEFAULT_PRIORITY);
    }

    /**
     * Registers an {@link IEntityComponentProvider} instance for appending {@link Component} to the tooltip.
     *
     * @param provider the data provider instance
     * @param position the position on the tooltip this applies to
     * @param clazz    the highest level class to apply to
     * @param priority the priority of this provider <b>0 is the minimum, lower number will be called first</b>
     *
     * @see #DEFAULT_PRIORITY
     */
    @ApiSide.ClientOnly
    <T> void addComponent(IEntityComponentProvider provider, TooltipPosition position, Class<T> clazz, int priority);

    /**
     * Registers an {@link IEntityComponentProvider} instance with {@link #DEFAULT_PRIORITY} for appending {@link Component} to the tooltip.
     *
     * @param provider the data provider instance
     * @param position the position on the tooltip this applies to
     * @param clazz    the highest level class to apply to
     */
    @ApiSide.ClientOnly
    default <T> void addComponent(IEntityComponentProvider provider, TooltipPosition position, Class<T> clazz) {
        addComponent(provider, position, clazz, DEFAULT_PRIORITY);
    }

    /**
     * Registers an {@link IServerDataProvider<Entity>} instance for data syncing purposes.
     *
     * @param provider the data provider instance
     * @param clazz    the highest level class to apply to
     */
    @ApiSide.ServerOnly
    <T, E extends Entity> void addEntityData(IServerDataProvider<E> provider, Class<T> clazz);

    /**
     * @deprecated use {@link #addIcon(IBlockComponentProvider, Class, int)} and {@link IBlockComponentProvider#getIcon(IBlockAccessor, IPluginConfig)}
     */
    @Deprecated
    <T> void addDisplayItem(IBlockComponentProvider provider, Class<T> clazz, int priority);

    /**
     * @deprecated use {@link #addIcon(IBlockComponentProvider, Class, int)} and {@link IBlockComponentProvider#getIcon(IBlockAccessor, IPluginConfig)}
     */
    @Deprecated
    default <T> void addDisplayItem(IBlockComponentProvider provider, Class<T> clazz) {
        addDisplayItem(provider, clazz, DEFAULT_PRIORITY);
    }

    /**
     * TODO: Remove
     *
     * @deprecated use {@link #addDisplayItem(IBlockComponentProvider, Class)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    default <T> void registerStackProvider(IComponentProvider provider, Class<T> clazz) {
        addDisplayItem(provider, clazz);
    }

    /**
     * TODO: Remove
     *
     * @deprecated use {@link #addComponent(IBlockComponentProvider, TooltipPosition, Class)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    default <T> void registerComponentProvider(IComponentProvider provider, TooltipPosition position, Class<T> clazz) {
        addComponent(provider, position, clazz);
    }

    /**
     * TODO: Remove
     *
     * @deprecated use {@link ITooltipComponent}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.20")
    void addRenderer(ResourceLocation id, ITooltipRenderer renderer);

    /**
     * TODO: Remove
     *
     * @deprecated use {@link #addBlockData(IServerDataProvider, Class)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    default <T> void registerBlockDataProvider(IServerDataProvider<BlockEntity> provider, Class<T> clazz) {
        addBlockData(provider, clazz);
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.20")
    <T> void addDisplayItem(IEntityComponentProvider provider, Class<T> clazz, int priority);

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.20")
    default <T> void addDisplayItem(IEntityComponentProvider provider, Class<T> clazz) {
        addDisplayItem(provider, clazz, DEFAULT_PRIORITY);
    }

    /**
     * TODO: Remove
     *
     * @deprecated use {@link #addOverride(IEntityComponentProvider, Class)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    default <T> void registerOverrideEntityProvider(IEntityComponentProvider provider, Class<T> entity) {
        addOverride(provider, entity);
    }

    /**
     * TODO: Remove
     *
     * @deprecated use {@link #addDisplayItem(IEntityComponentProvider, Class)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    default <T> void registerEntityStackProvider(IEntityComponentProvider provider, Class<T> entity) {
        addDisplayItem(provider, entity);
    }

    /**
     * TODO: Remove
     *
     * @deprecated use {@link #addComponent(IEntityComponentProvider, TooltipPosition, Class)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    default <T> void registerComponentProvider(IEntityComponentProvider provider, TooltipPosition position, Class<T> clazz) {
        addComponent(provider, position, clazz);
    }

    /**
     * TODO: Remove
     *
     * @deprecated use {@link #addEntityData(IServerDataProvider, Class)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    default <T> void registerEntityDataProvider(IServerDataProvider<Entity> provider, Class<T> clazz) {
        addEntityData(provider, clazz);
    }

    /**
     * TODO: Remove
     *
     * @deprecated use {@link #addRenderer(ResourceLocation, ITooltipRenderer)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    default void registerTooltipRenderer(ResourceLocation id, ITooltipRenderer renderer) {
        addRenderer(id, renderer);
    }

    /**
     * @deprecated use {@link #addSyncedConfig(ResourceLocation, boolean, boolean)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.20")
    default void addSyncedConfig(ResourceLocation key, boolean defaultValue) {
        addSyncedConfig(key, defaultValue, defaultValue);
    }

    /**
     * @deprecated use {@link #addSyncedConfig(ResourceLocation, int, int)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.20")
    default void addSyncedConfig(ResourceLocation key, int defaultValue) {
        addSyncedConfig(key, defaultValue, defaultValue);
    }

    /**
     * @deprecated use {@link #addSyncedConfig(ResourceLocation, double, double)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.20")
    default void addSyncedConfig(ResourceLocation key, double defaultValue) {
        addSyncedConfig(key, defaultValue, defaultValue);
    }

    /**
     * @deprecated use {@link #addSyncedConfig(ResourceLocation, String, String)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.20")
    default void addSyncedConfig(ResourceLocation key, String defaultValue) {
        addSyncedConfig(key, defaultValue, defaultValue);
    }

    /**
     * @deprecated use {@link #addSyncedConfig(ResourceLocation, Enum, Enum)}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.20")
    default <T extends Enum<T>> void addSyncedConfig(ResourceLocation key, T defaultValue) {
        addSyncedConfig(key, defaultValue, defaultValue);
    }

}
