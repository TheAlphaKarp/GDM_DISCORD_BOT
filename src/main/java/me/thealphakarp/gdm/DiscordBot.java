package me.thealphakarp.gdm;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class DiscordBot {

    private DiscordBot() throws LoginException {
        WebUtils.setUserAgent("GDM Discord bot");
        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                .setColor(0x3883d9)
                .setFooter("GDM Bot")
        );

        JDABuilder.createDefault(
                Config.get("TOKEN"),
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES
        )
                .disableCache(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOTE
                ))
                .enableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(new Listener())
                .setActivity(Activity.watching(Config.get("ACTIVITY")))
                .build();
    }

    public static void main(String[] args) throws LoginException {
        new DiscordBot();
    }
}