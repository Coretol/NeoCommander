package net.propromp.neocommander.api.argument

import com.mojang.brigadier.arguments.ArgumentType
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.arguments.selector.EntitySelector
import net.propromp.neocommander.api.NeoCommandContext
import org.bukkit.entity.Player

class PlayerArgument(name: String) :
    AbstractEntityArgument<Player>(name) {

    override fun asBrigadier(): ArgumentType<out Any> {
        return EntityArgument.player()
    }

    override fun parse(context: NeoCommandContext, t: Any): Player {
        return (t as EntitySelector).findSinglePlayer(context.context.source as CommandSourceStack).bukkitEntity
    }
}