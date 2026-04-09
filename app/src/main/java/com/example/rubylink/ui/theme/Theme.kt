package com.example.rubylink.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Цвета
val RubyPrimary = Color(0xFF9B111E)
val RubyOnPrimary = Color.White
val RubyBackground = Color(0xFF0D0D0D)
val RubySurface = Color(0xFF1C1C1E)
val RubyOnSurface = Color(0xFFEAEAEA)

// Шрифты
val RubyFontFamily = FontFamily.Default

// Типография
val RubyTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = RubyFontFamily,
        fontSize = 16.sp,
        color = RubyOnSurface
    ),
    bodyMedium = TextStyle(
        fontFamily = RubyFontFamily,
        fontSize = 14.sp,
        color = RubyOnSurface
    ),
    bodySmall = TextStyle(
        fontFamily = RubyFontFamily,
        fontSize = 12.sp,
        color = RubyOnSurface
    )
)

// Формы
val RubyShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp)
)

// Тёмная цветовая схема
private val DarkColorScheme = darkColorScheme(
    primary = RubyPrimary,
    onPrimary = RubyOnPrimary,
    background = RubyBackground,
    surface = RubySurface,
    onSurface = RubyOnSurface
)

@Composable
fun RubyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = RubyTypography,
        shapes = RubyShapes,
        content = content
    )
}