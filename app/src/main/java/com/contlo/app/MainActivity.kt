package com.contlo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.contlo.app.ui.ListOfPullRequests
import com.contlo.app.ui.mockPr
import com.contlo.app.ui.theme.ContloAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContloAppTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
    ContloAppTheme {
        ListOfPullRequests(
            List(100) { index ->
                mockPr.copy(id = index.toString(), userName = index.toString())
            })
        
    }
}

@Preview
@Composable
fun MainAppPreview() {
    ContloAppTheme {
        ListOfPullRequests(
            List(100) { index ->
                mockPr.copy(id = index.toString(), userName = index.toString())
            })

    }
}