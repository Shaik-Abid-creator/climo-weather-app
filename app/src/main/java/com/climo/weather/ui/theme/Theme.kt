package com.climo.weather.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF00D4FF),
    onPrimary = Color(0xFF003D5C),
    primaryContainer = Color(0xFF005A82),
    onPrimaryContainer = Color(0xFFCCE5FF),
    secondary = Color(0xFFFFB68A),
    onSecondary = Color(0xFF5A2C00),
    secondaryContainer = Color(0xFF7A4100),
    onSecondaryContainer = Color(0xFFFFDCC8),
    tertiary = Color(0xFF7CDFBF),
    onTertiary = Color(0xFF003D2E),
    tertiaryContainer = Color(0xFF055344),
    onTertiaryContainer = Color(0xFFA6F3D0),
    error = Color(0xFFF87171),
    onError = Color(0xFF660005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFCDAD6),
    background = Color(0xFF0F1419),
    onBackground = Color(0xFFE8EEF5),
    surface = Color(0xFF1A1F26),
    onSurface = Color(0xFFE8EEF5),
    surfaceVariant = Color(0xFF49454E),
    onSurfaceVariant = Color(0xFFCAC7D0),
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454E),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFFE8EEF5),
    inverseOnSurface = Color(0xFF0F1419),
    inversePrimary = Color(0xFF0284C7)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0284C7),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFCCE5FF),
    onPrimaryContainer = Color(0xFF001D3C),
    secondary = Color(0xFFEA580C),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFDCC8),
    onSecondaryContainer = Color(0xFF3A1800),
    tertiary = Color(0xFF10B981),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFA6F3D0),
    onTertiaryContainer = Color(0xFF00251A),
    error = Color(0xFFEF4444),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFCDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF0F172A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFDFE2EB),
    onSurfaceVariant = Color(0xFF49454E),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFC9C7CF),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF1A1F26),
    inverseOnSurface = Color(0xFFF0F4F8),
    inversePrimary = Color(0xFF00D4FF)
)

@Composable
fun ClimoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
