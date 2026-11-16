package com.recolorhphud;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;
import net.runelite.client.ui.ColorScheme;

@ConfigGroup("recolorhphud")
public interface RecolorHpHudConfig extends Config
{
	@ConfigItem(
		keyName = "nameColor",
		name = "Name Color",
		description = "",
		position = 0
	)
	default Color nameColor()
	{
		return ColorScheme.BRAND_ORANGE;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "nameOpacity",
		name = " - Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 1
	)
	default int nameOpacity()
	{
		return 255;
	}

	@ConfigItem(
		keyName = "nameShadowed",
		name = " - Shadow",
		description = "",
		position = 2
	)
	default boolean nameShadowed()
	{
		return true;
	}

	@ConfigItem(
		keyName = "valueColor",
		name = "Value Color",
		description = "",
		position = 3
	)
	default Color valueColor()
	{
		return Color.YELLOW;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "valueOpacity",
		name = " - Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 4
	)
	default int valueOpacity()
	{
		return 255;
	}

	@ConfigItem(
		keyName = "valueShadowed",
		name = " - Shadow",
		description = "",
		position = 5
	)
	default boolean valueShadowed()
	{
		return true;
	}

	@ConfigItem(
		keyName = "barForegroundColor",
		name = "Bar Foreground Color",
		description = "",
		position = 6
	)
	default Color barForegroundColor()
	{
		return new Color(0, 170, 0);
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "barForegroundOpacity",
		name = " - Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 7
	)
	default int barForegroundOpacity()
	{
		return 255;
	}

	@ConfigItem(
		keyName = "barBackgroundColor",
		name = "Bar Background Color",
		description = "",
		position = 8
	)
	default Color barBackgroundColor()
	{
		return new Color(170, 0, 0);
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "barBackgroundOpacity",
		name = " - Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 9
	)
	default int barBackgroundOpacity()
	{
		return 255;
	}

	@ConfigItem(
		keyName = "barProgressColor",
		name = "Bar Sliding Color",
		description = "",
		position = 10
	)
	default Color barProgressColor()
	{
		return ColorScheme.PROGRESS_INPROGRESS_COLOR;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "barProgressOpacity",
		name = " - Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 11
	)
	default int barProgressOpacity()
	{
		return 255;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "frameOpacity",
		name = "Frame Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 12
	)
	default int frameOpacity()
	{
		return 255;
	}
}
