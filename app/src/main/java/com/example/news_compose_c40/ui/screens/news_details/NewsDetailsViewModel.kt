package com.example.news_compose_c40.ui.screens.news_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_compose_c40.R
import com.example.news_compose_c40.data.model.article.Article
import com.example.news_compose_c40.data.model.article.ArticlesResponse
import com.example.news_compose_c40.util.UIMessage
import com.example.news_compose_c40.util.fromJson
import com.example.news_compose_c40.data.api.NewsService
import com.example.news_compose_c40.data.repository.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(val newsRepo: NewsRepo, savedStateHandle: SavedStateHandle):ViewModel() {

    private val title:String? = savedStateHandle["title"]

    private val _article = mutableStateOf<Article?>(null)
    val article: Article? get() = _article.value

    private val _uiMessage = mutableStateOf(UIMessage())
    val uiMessage: UIMessage get() = _uiMessage.value

    private val _isErrorDialogVisible = mutableStateOf(true)
    val isErrorDialogVisible: Boolean get() = _isErrorDialogVisible.value

    fun hideErrorDialog() {
        _isErrorDialogVisible.value = false
    }
     fun getArticle(){

         viewModelScope.launch (Dispatchers.IO){
             try {
                 _uiMessage.value = UIMessage(isLoading = true)
                 if (title != null) {
                     val article = newsRepo.getArticle(title)
                     _article.value = article

                 }
                 _uiMessage.value = UIMessage(isLoading = false)
             } catch (e: HttpException) {
                 val articlesResponse = e.response()?.errorBody()?.string()?.fromJson(
                     ArticlesResponse::class.java
                 )
                 _uiMessage.value= UIMessage(
                     isLoading = false,
                     errorMessage = articlesResponse?.message,
                     retryAction = {
                         getArticle()
                     })


             } catch (e: UnknownHostException) {

                 _uiMessage.value = UIMessage(
                     isLoading = false,
                     errorMessageId = R.string.connection_error,
                     retryAction = {
                         getArticle()
                     })

             } catch (e: Exception) {
                 _uiMessage.value = UIMessage(
                     isLoading = false,
                     errorMessage = e.localizedMessage,
                     retryAction = {
                         getArticle()
                     })

             }


         }

    }
}
