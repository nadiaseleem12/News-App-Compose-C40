package com.example.news_compose_c40.ui.screens.news
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.news_compose_c40.R
import com.example.news_compose_c40.ui.screens.news_details.NewsDetailsViewModel
import com.example.news_compose_c40.util.getErrorMessage
import com.example.news_compose_c40.ui.widgets.ErrorDialog
import com.example.news_compose_c40.ui.widgets.NewsList
import com.example.news_compose_c40.ui.widgets.NewsTopAppBar
import com.example.news_compose_c40.ui.widgets.SourcesTabRow
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.Serializable

@Serializable
data class NewsRoute(val categoryID: String,
                     val categoryName: Int)


@Composable
fun NewsScreen(
    vm: NewsViewModel = hiltViewModel(),
    categoryID: String,
    categoryName: Int,
    scope: CoroutineScope,
    drawerState: DrawerState,
    onNewsClick: (String,String) -> Unit,
    onSearchClick: () -> Unit
) {
    if (vm.isErrorDialogVisible) {

        var errorMessage: String = getErrorMessage(vm.uiMessage.errorMessage, vm.uiMessage.errorMessageId)

        if (vm.isErrorDialogVisible) {
            ErrorDialog(
                errorMessage,
                onRetry = vm.uiMessage.retryAction,
                onDismiss = { vm.hideErrorDialog() }
            )
        }
    }

    Scaffold(topBar = {
        NewsTopAppBar(
            shouldDisplaySearchIcon = true,
            shouldDisplayMenuIcon = true,
            titleResourceId = categoryName,
            scope = scope,
            drawerState = drawerState,
            onSearchClick = {
                onSearchClick()
            }
        )

    }) { paddingValues: PaddingValues ->

        LaunchedEffect(key1 =null) {
            vm.getSources(categoryID)
        }
        // Call `getNewsBySource` for the first source once sources are fetched
        LaunchedEffect(key1 = vm.sourcesList) {
            if (!vm.sourcesList.isNullOrEmpty()) {
                vm.sourcesList!![0].id?.let { vm.getNewsBySource(it) }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .paint(
                    painterResource(id = R.drawable.bg_pattern),
                    contentScale = ContentScale.Crop
                )
        ) {

            vm.sourcesList?.let {
                SourcesTabRow(
                    it,
                    onTabClicked = { sourceId ->
                        vm.getNewsBySource(sourceId)
                    }
                )
            }


                NewsList(
                    vm.articlesList,
                    vm.uiMessage.shouldDisplayNoArticlesFound,
                    vm.uiMessage.isLoading,
                    onNewsClick
                )



        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewsFragmentScreen() {
    NewsScreen(
        categoryName = R.string.business,
        categoryID = "business",
        scope = rememberCoroutineScope(),
        drawerState = rememberDrawerState(
            initialValue = DrawerValue.Closed
        ),
        onSearchClick = {},
        onNewsClick = {_,_->

        }
    )

}
