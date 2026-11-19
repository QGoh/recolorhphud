package com.recolorhphud;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ScriptID;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.ScriptPreFired;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Recolor HP Hud"
)
public class RecolorHpHudPlugin extends Plugin
{
	public static final int HP_HUD_GREEN = 51200;
	public static final int HP_HUD_RED = 13107200;
	public static final int HP_HUD_DARK_GREEN = 36864;

	public static final int HP_HUD_FADE_IN = 2887;
	public static final int HP_HUD_OPEN = 2376;


	public static final ImmutableSet<Integer> HP_HUD_SCRIPT_IDS = ImmutableSet.of(
		HP_HUD_FADE_IN,
		HP_HUD_OPEN,
		ScriptID.HP_HUD_UPDATE
	);

	@Inject
	private Client client;

	@Inject
	private RecolorHpHudConfig config;

	private int originalBarForeground = 0;
	private int originalBarBackground = 0;
	private int originalBarProgress = 0;

	@Subscribe
	public void onBeforeRender(BeforeRender beforeRender)
	{
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
			if (originalBarForeground == HP_HUD_GREEN)
			{
				barForeground.setTextColor(config.barForegroundColor().getRGB());
			}
			barForeground.setOpacity(255 - config.barForegroundOpacity());
		}

		Widget barBackground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_BACK);
		if (barBackground != null)
		{
			if (originalBarBackground == HP_HUD_RED)
			{
				barBackground.setTextColor(config.barBackgroundColor().getRGB());
			}
			barBackground.setOpacity(255 - config.barBackgroundOpacity());
		}

		Widget barProgress = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_SLIDING);
		if (barProgress != null)
		{
			if (originalBarProgress == HP_HUD_DARK_GREEN)
			{
				barProgress.setTextColor(config.barProgressColor().getRGB());
			}
			barProgress.setOpacity(255 - config.barProgressOpacity());
		}

		Widget barBorder = client.getWidget(InterfaceID.HpbarHud.INNER_BORDER);
		if (barBorder != null)
		{
			barBorder.setTextColor(config.barBorderColor().getRGB());
			barBorder.setOpacity(255 - config.barBorderOpacity());
		}

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

		Widget extraBarForeground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_REMAINING_EXTRA);
		if (extraBarForeground != null)
		{
			extraBarForeground.setOpacity(255 - config.barForegroundOpacity());
		}

		Widget extraBarBackground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_BACK_EXTRA);
		if (extraBarBackground != null)
		{
			extraBarBackground.setOpacity(255 - config.barBackgroundOpacity());
		}

		Widget extraBarProgress = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_SLIDING_EXTRA);
		if (extraBarProgress != null)
		{
			extraBarProgress.setOpacity(255 - config.barProgressOpacity());
		}

		Widget extraBarBorder = client.getWidget(InterfaceID.HpbarHud.INNER_BORDER_EXTRA);
		if (extraBarBorder != null)
		{
			extraBarBorder.setTextColor(config.barBorderColor().getRGB());
			extraBarBorder.setOpacity(255 - config.barBorderOpacity());
		}
	}

	@Subscribe
	public void onScriptPreFired(ScriptPreFired scriptPreFired)
	{
		if (!HP_HUD_SCRIPT_IDS.contains(scriptPreFired.getScriptId()))
		{
			return;
		}

		Widget barForeground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_REMAINING);
		if (barForeground != null)
		{
			originalBarForeground = barForeground.getTextColor();
		}

		Widget barBackground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_BACK);
		if (barBackground != null)
		{
			originalBarBackground = barBackground.getTextColor();
		}

		Widget barProgress = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_SLIDING);
		if (barProgress != null)
		{
			originalBarProgress = barProgress.getTextColor();
		}
	}

	@Provides
	RecolorHpHudConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RecolorHpHudConfig.class);
	}
}
