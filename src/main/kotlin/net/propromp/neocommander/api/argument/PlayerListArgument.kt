package net.propromp.neocommander.api.argument

import com.mojang.brigadier.arguments.ArgumentType
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.arguments.selector.EntitySelector
import net.propromp.neocommander.api.NeoCommandContext
import org.bukkit.entity.Player

class PlayerListArgument(name: String) :
    AbstractEntityArgument<List<Player>>(name) {

    override fun asBrigadier(): ArgumentType<out Any> {
        return EntityArgument.players()
    }

    override fun parse(context: NeoCommandContext, t: Any): List<Player> {
        return (t as EntitySelector).findPlayers(context.context.source as CommandSourceStack)
            .map { player -> player.bukkitEntity }
    }
}