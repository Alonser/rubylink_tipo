package com.example.rubylink.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 🔴 Основной цвет (рубин)
val RubyPrimary = Color(0xFF9B111E)

// ⚫ Фон
val RubyBackground = Color(0xFF0D0D0D)

// 🧱 Карточки / элементы
val RubySurface = Color(0xFF1C1C1E)

// 🤍 Текст
val RubyOnSurface = Color(0xFFFFFFFF)

// 🔘 Secondary (акценты)
val RubySecondary = Color(0xFF2A2A2D)

// 🌑 Цветовая схема
private val DarkColorScheme = darkColorScheme(
    primary = RubyPrimary,
    onPrimary = Color.White,

    background = RubyBackground,
    onBackground = RubyOnSurface,

    surface = RubySurface,
    onSurface = RubyOnSurface,

    secondary = RubySecondary,
    onSecondary = RubyOnSurface
)

// ✍️ Шрифты
val RubyTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        color = RubyOnSurface
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 14.sp,
        color = RubyOnSurface
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 12.sp,
        color = RubyOnSurface
    )
)

// 🔲 Скругления (как в Telegram)
val RubyShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp)
)

// 🎨 Тема
@Composable
fun RubyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = RubyTypography,
        shapes = RubyShapes,
        content = content
    )
}