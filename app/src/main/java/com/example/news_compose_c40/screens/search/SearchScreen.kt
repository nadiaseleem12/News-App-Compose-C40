package com.example.news_compose_c40.screens.search

import android.database.Cursor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news_compose_c40.R
import com.example.news_compose_c40.ui.theme.green
import com.example.news_compose_c40.ui.theme.green_50
import kotlinx.serialization.Serializable

@Serializable
object SearchRoute

@Composable
fun SearchScreen(vm:SearchViewModel= hiltViewModel()){
    val focusManager= LocalFocusManager.current
    val SearchTextFieldFocusRequester= remember {
        FocusRequester()
    }
    LaunchedEffect(key1 = true) {
        SearchTextFieldFocusRequester.requestFocus()
    }
    Column(modifier = Modifier
        .fillMaxHeight(.1f)
        .fillMaxWidth()
        .statusBarsPadding()
        .paint(painterResource(R.drawable.bg_pattern), contentScale = ContentScale.Crop)) {
        Box(modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
            .background(green)
            .padding(horizontal = 30.dp)
            .focusRequester(SearchTextFieldFocusRequester)
            .onFocusChanged { focusState ->
                vm.setIsFocused(focusState.isFocused)
            }
            .align(Alignment.CenterHorizontally))
    }
    TextField(value =vm.SearchQuery, onValueChange = {vm.setSearchQuery(it)}
        , colors = TextFieldDefaults.colors(
            focusedTextColor = green, unfocusedTextColor = green_50, focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, cursorColor = green
        ), modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp)), placeholder = {
            Text(stringResource(R.string.search_articles))
        }, keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ), keyboardActions = KeyboardActions(onSearch = {

        }), leadingIcon = {
            Image(painterResource(id = R.drawable.search_icon)  , contentDescription = stringResource(id = R.string.search),
                modifier = Modifier.clickable { })
        }, trailingIcon = {if (vm.isfocused){
            Image(painterResource(id = R.drawable.close_icon)  , contentDescription = stringResource(id = R.string.close),
                modifier = Modifier.clickable {
                    if (vm.SearchQuery.isNotEmpty()){
                        vm.setSearchQuery("")
                    }else focusManager.clearFocus()
                })
        } else {

        }}
    )
}
@Preview(showSystemUi = true)
@Composable
private fun SearchScreenPreview(){
    SearchScreen()
}