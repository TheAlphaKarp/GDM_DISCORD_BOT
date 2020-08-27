package me.thealphakarp.gdm.command.commands.gdm;

import me.thealphakarp.gdm.command.CommandContext;
import me.thealphakarp.gdm.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class Register implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member member = ctx.getMember();

        if (ctx.getArgs().size() < 3) {
            channel.sendMessage("You didn't give enough arguments. Please use the following template:" +
                    ">register voornaam naam richting \n example: >register Arne Six @MMP-1st-bachelor").queue();
            return;
        }

        final String firstName = ctx.getArgs().get(0);
        final String lastName = ctx.getArgs().get(1);
        final String requestedRole = parseRole(ctx.getArgs().get(2));

        if (!channel.getId().equals("685416873315401760")) {
            channel.sendMessage("Sorry but this is the wrong channel. Please go to #registreren instead.").queue();
            return;
        }

        if (requestedRole.equals("504307761673273346")) {
            channel.sendMessage("Funny... don't try this again...").queue();
            return;
        }

        final List<Role> memberRoles = member.getRoles();
        final Role role = ctx.getGuild().getRoleById(requestedRole);

        if (role == null) {
            channel.sendMessage("The role doesn't exist. You probably entered mentioned a user.").queue();
            return;
        }

        if (memberRoles.size() != 0) {
            try {
                ctx.getGuild().modifyMemberRoles(member, null, memberRoles).queue();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            ctx.getGuild().addRoleToMember(member.getId(), role).complete();
        } catch (Exception e) {
            System.out.println(e);
        }


        final List<Role> registered = ctx.getGuild().getRolesByName("registered", true);
        final List<Role> general = ctx.getGuild().getRolesByName("algemeen", true);
        System.out.println(registered.get(0).getName());
        ctx.getGuild().addRoleToMember(member.getId(), registered.get(0)).complete();
        ctx.getGuild().addRoleToMember(member.getId(), general.get(0)).complete();

        ctx.getMember().modifyNickname(firstName + " " + lastName).complete();
    }

    @Override
    public String getName() {
        return "register";
    }

    public String parseRole(String mentionedRole) {
        return mentionedRole.substring(3, mentionedRole.length() - 1);
    }


    @Override
    public String getHelp() {
        return "Register for a role in the discord server.";
    }
}
