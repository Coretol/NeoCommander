package net.propromp.neocommander.api.argument

import com.mojang.brigadier.arguments.ArgumentType
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.arguments.selector.EntitySelector
import net.propromp.neocommander.api.NeoCommandContext

abstract class AbstractEntityArgument<T>(name: String, val type: Pair<Boolean, Boolean>) :
    NeoArgument<Any, T>(name) {
    companion object {
        val SINGLE_PLAYER = true to true
        val SINGLE_ENTITY = true to false
        val SEVERAL_PLAYERS = false to true
        val SEVERAL_ENTITIES = false to false
    }

    final override fun asBrigadier() = EntityArgument::class.java.getDeclaredConstructor(
        *arrayOf(
            Boolean::class.javaPrimitiveType!!,
            Boolean::class.javaPrimitiveType!!
        )
    ).apply {
        isAccessible = true
    }.newInstance(type.first, type.second) as ArgumentType<out Any>

    final override fun parse(context: NeoCommandContext, t: Any) = when (type) {
        SINGLE_PLAYER -> {
            (t as EntitySelector).findSinglePlayer(context.context.source as CommandSourceStack).bukkitEntity
        }

        SINGLE_ENTITY -> {
            (t as EntitySelector).findSingleEntity(context.context.source as CommandSourceStack).bukkitEntity
        }

        SEVERAL_PLAYERS -> {
            val players = (t as EntitySelector).findPlayers(context.context.source as CommandSourceStack)
            players.map { it.bukkitEntity }
        }
        SEVERAL_ENTITIES -> {
            val players = (t as EntitySelector).findEntities(context.context.source as CommandSourceStack)
            players.map { it.bukkitEntity }
        }
        else -> null!!
    } as T


}