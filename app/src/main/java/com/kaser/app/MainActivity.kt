package com.kaser.app.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen() {
    // قائمة بالنكات عن البرمجة والتعب على الهاتف
    val jokes = remember {
        listOf(
            "تعبت وأنا أطور التطبيق من الجوال.. حتى البطارية دعت عليّ! 📱😂",
            "سألت الذكاء الاصطناعي: متى يجهز الكاسر آب؟ قال لي: لما تشتري لابتوب! 💻😜",
            "التطبيق شغّال بالبركة والخطأ 400 سلم عليّ وراح! 🚀😁",
            "أسرع تطبيق شات في العالم.. بس ينتظر النت يتحسن! 🌐⚡"
        )
    }

    var currentJokeIndex by remember { mutableStateOf(0) }

    // تغيير النكتة تلقائياً كل 5 ثوانٍ
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            currentJokeIndex = (currentJokeIndex + 1) % jokes.size
        }
    }

    // تدرج ألوان فخم للعنوان
    val titleGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFFFD700), // ذهبي
            Color(0xFFFF4500), // برتقالي ناري
            Color(0xFFFF1493)  // وردي ملكي
        )
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF121212) // خلفية داكنة مريحة
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // --- 1. العنوان الضخم المتدرج ---
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "الكاسر للتواصل",
                    style = TextStyle(
                        brush = titleGradient,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = "Kaser App - الإصدار الملكي",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // --- 2. كارت النكت اليومية والتعب ---
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "💡 نكتة اليوم على التعب:",
                        color = Color(0xFFFFD700),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    AnimatedContent(targetState = jokes[currentJokeIndex], label = "JokeAnimation") { jokeText ->
                        Text(
                            text = jokeText,
                            color = Color.White,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // --- 3. أزرار الدخول السريعة ---
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { /* الانتقال لغرف المحادثة */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4500))
                ) {
                    Text(
                        text = "دخول الدردشة الفورية 🚀",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                OutlinedButton(
                    onClick = { /* تفعيل العزلة */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "تفعيل ميزة العزلة 🔕",
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}