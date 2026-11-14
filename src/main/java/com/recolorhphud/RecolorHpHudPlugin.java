package com.recolorhphud;

import com.google.inject.Provides;
import java.awt.Color;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Recolor HP Hud"
)
public class RecolorHpHudPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RecolorHpHudConfig config;

	@Subscribe
	public void onBeforeRender(BeforeRender beforeRender)
	{
		Widget border = client.getWidget(InterfaceID.HpbarHud.OUTER_BORDER);
		if (border != null)
		{
			border.setOpacity(255 - config.frameOpacity());
		}

		Widget frame = client.getWidget(InterfaceID.HpbarHud.NAME_BACKING);
		if (frame != null)
		{
			frame.setOpacity(255 - config.frameOpacity());
		}

		Widget name = client.getWidget(InterfaceID.HpbarHud.CREATURE_NAME);
		if (name != null)
		{
			name.setTextColor(config.nameColor().getRGB());
			name.setOpacity(255 - config.nameOpacity());
			name.setTextShadowed(config.nameShadowed());
		}

		Widget value = client.getWidget(InterfaceID.HpbarHud.HP_BAR_TEXT);
		if (value != null)
		{
			value.setTextColor(config.valueColor().getRGB());
			value.setOpacity(255 - config.valueOpacity());
			value.setTextShadowed(config.valueShadowed());
		}

		Widget barForeground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_REMAINING);
		if (barForeground != null)
		{
			barForeground.setTextColor(config.barForegroundColor().getRGB());
			barForeground.setOpacity(255 - config.barForegroundOpacity());
		}

		Widget barBackground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_BACK);
		if (barBackground != null)
		{
			barBackground.setTextColor(config.barBackgroundColor().getRGB());
			barBackground.setOpacity(255 - config.barBackgroundOpacity());
		}

		Widget barProgress = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_SLIDING);
		if (barProgress != null)
		{
			barProgress.setTextColor(config.barProgressColor().getRGB());
			barProgress.setOpacity(255 - config.barProgressOpacity());
		}
	}

	@Provides
	RecolorHpHudConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RecolorHpHudConfig.class);
	}
}
