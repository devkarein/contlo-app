package com.contlo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.contlo.app.ui.ErrorContainerView
import com.contlo.app.ui.ListOfPullRequests
import com.contlo.app.ui.LoadingView
import com.contlo.app.ui.MainViewModel
import com.contlo.app.ui.mockPr
import com.contlo.app.ui.theme.ContloAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContloAppTheme {
                MainApp(viewModel)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadPullRequests()
    }
}

@Composable
fun MainApp(
    viewModel: MainViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val pullReqestData by viewModel.prUiState.collectAsState()
    PrContainerView(
        pullReqestData,
        onRetry = { viewModel.loadPullRequests() },
        modifier = modifier.fillMaxSize(1f)
    )
}

@Composable
fun PrContainerView(
    data: MainViewModel.UiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (data is MainViewModel.UiState.Loading) {
        LoadingView()
    } else if (data is MainViewModel.UiState.Error) {
        ErrorContainerView(onRetry = onRetry)
    } else {
        if (data is MainViewModel.UiState.Success) ListOfPullRequests(
            prItems = data.data, modifier = modifier
        )
    }
}

@Preview
@Composable
fun MainAppPreview() {
    ContloAppTheme {
        ListOfPullRequests(
            List(100) { index ->
                mockPr.copy(id = index, userName = index.toString())
            })

    }
}