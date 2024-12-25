package net.propromp.neocommander.api.argument

import com.mojang.brigadier.arguments.ArgumentType
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.arguments.item.ItemArgument
import net.minecraft.commands.arguments.item.ItemInput
import net.minecraft.server.MinecraftServer
import net.minecraft.world.level.WorldDataConfiguration
import net.minecraft.world.level.storage.WorldData
import net.propromp.neocommander.api.NeoCommandContext
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftServer
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

private val COMMAND_BUILDER_CONTEXT = run {
    val server = (Bukkit.getServer() as CraftServer).handle.server
    val worldDataMethod = MinecraftServer::class.java.getMethod("getWorldData")
    val worldData = worldDataMethod.invoke(server) as WorldData
    val getDataConfigurationMethod = WorldData::class.java.getMethod("getDataConfiguration")
    val dataConfiguration = getDataConfigurationMethod.invoke(worldData) as WorldDataConfiguration
    CommandBuildContext.simple(
        server.registryAccess(),
        dataConfiguration.enabledFeatures()
    )
}

@Suppress("UnstableApiUsage")
class ItemStackArgument(name: String) : NeoArgument<Any, ItemStack>(name) {
    override fun asBrigadier(): ArgumentType<*> = ItemArgument.item(COMMAND_BUILDER_CONTEXT)

    override fun parse(context: NeoCommandContext, t: Any): ItemStack {
        val itemStack = (t as ItemInput).createItemStack(1, false)

        return CraftItemStack.asBukkitCopy(itemStack)
    }
}