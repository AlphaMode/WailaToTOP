package me.alphamode.topcompact;

import mcp.mobius.waila.api.IServerAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ServerAccessor implements IServerAccessor<Object> {
    private Level world;
    private ServerPlayer player;
    private HitResult hitResult;
    private Object target;

    @SuppressWarnings("unchecked")
    public ServerAccessor(Level world, ServerPlayer player, HitResult hitResult, Object target) {
        this.world = world;
        this.player = player;
        this.hitResult = hitResult;
        this.target = target;
    }

    @Override
    public Level getWorld() {
        return world;
    }

    @Override
    public ServerPlayer getPlayer() {
        return player;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <H extends HitResult> H getHitResult() {
        return (H) hitResult;
    }

    @Override
    public Object getTarget() {
        return target;
    }

}