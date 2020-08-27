package me.thealphakarp.gdm.command.commands.admin;

import me.thealphakarp.gdm.command.CommandContext;
import me.thealphakarp.gdm.command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Message message = ctx.getMessage();
        final Member member = ctx.getMember();
        final List<String> args = ctx.getArgs();
        
        if (!member.hasPermission(Permission.MANAGE_ROLES)) {
            channel.sendMessage("You are missing permissions to manage these roles.").queue();
            return;
        }

        System.out.println(ctx.getGuild().getMemberCount());

        final List<Member> members = ctx.getGuild().getMembers();
        final List<Role> generalRole = ctx.getGuild().getRolesByName("algemeen", true);

        System.out.println(members.size());

        for (var m: members) {
            System.out.println(m.getUser().getName() + "'s roles");
            for (var r: m.getRoles()) {
                System.out.println(r.getName());
            }
            System.out.println("</roles>");

            System.out.println("ROLES TO DELETE");
            final List<Role> collect = m.getRoles().stream().filter(item -> !item.getName().toLowerCase().equals("dsqd"))
                    .collect(Collectors.toList());

            for (var r: collect) {
                System.out.println(r.getName());
            }
            System.out.println("END ROLES TO DELETE");

            ctx.getGuild().modifyMemberRoles(m, generalRole, collect).queue();
        }

    }

    @Override
    public String getName() {
        return "order";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
