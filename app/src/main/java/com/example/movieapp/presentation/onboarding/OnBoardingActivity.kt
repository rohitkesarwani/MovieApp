package com.example.movieapp.presentation.onboarding

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.presentation.screen.main.MainActivity
import com.example.movieapp.presentation.ui.theme.MovieAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val windowInsetsController = WindowInsetsControllerCompat(
            window, window.decorView
        )
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        lifecycleScope.launch {
            delay(6000)
            val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Blue)) {
                        val videoView: VideoView
                        val context = LocalContext.current
                        AndroidView(factory = {ctx->
                            VideoView(context).apply {
                                val uri = "android.resource://" + context.getPackageName() + "/" + R.raw.splash_vid
                                setVideoURI(Uri.parse(uri))
                                scaleX = 1f
                                scaleY = 3.8f
                                start()
                            }
                        }, modifier = Modifier.fillMaxSize())

                    }
                }
            }
        }
    }
}
