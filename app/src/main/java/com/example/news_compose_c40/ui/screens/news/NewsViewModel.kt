package com.example.news_compose_c40.ui.screens.news

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_compose_c40.R
import com.example.news_compose_c40.data.model.article.Article
import com.example.news_compose_c40.data.model.source.Source
import com.example.news_compose_c40.data.model.source.SourcesResponse
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
class NewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo
) : ViewModel() {
    private val _sourcesList = mutableStateOf<List<Source>?>(null)
    val sourcesList: List<Source>? get() = _sourcesList.value

    private var _articlesList =mutableStateOf<List<Article>?>(null)
    val articlesList: List<Article>? get() = _articlesList.value

    private val _uiMessage = mutableStateOf(UIMessage())
    val uiMessage: UIMessage get() = _uiMessage.value

    private val _isErrorDialogVisible = mutableStateOf(false)
    val isErrorDialogVisible: Boolean get() = _isErrorDialogVisible.value

    fun showErrorDialog() {
        _isErrorDialogVisible.value = true
    }

    fun hideErrorDialog() {
        _isErrorDialogVisible.value = false
    }
    fun getSources(categoryId: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiMessage.value = UIMessage(isLoading = true)
                val sources = newsRepo.getSources(categoryId = categoryId)
                _uiMessage.value = UIMessage(isLoading = false)

                if (sources != null) {
                    _sourcesList.value = sources
                }


            } catch (e: HttpException) {
                val sourcesResponse = e.response()?.errorBody()?.string()?.fromJson(
                    SourcesResponse::class.java
                )
                _uiMessage.value= UIMessage(
                    isLoading = false,
                    errorMessage = sourcesResponse?.message,
                    retryAction = {
                        getSources(categoryId)
                    })


            } catch (e: UnknownHostException) {

                _uiMessage.value = UIMessage(
                        isLoading = false,
                        errorMessageId = R.string.connection_error,
                        retryAction = {
                            getSources(categoryId)
                        })

            } catch (e: Exception) {
                _uiMessage.value = UIMessage(
                        isLoading = false,
                        errorMessage = e.localizedMessage,
                        retryAction = {
                            getSources(categoryId)
                        })

            }finally {
                hideErrorDialog()
            }
        }
    }


    fun getNewsBySource(sourceId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiMessage.value = UIMessage(isLoading = true)
                val articles = newsRepo.getArticles(sourceId =sourceId)
                _uiMessage.value = UIMessage(isLoading = false)

                if (!articles.isNullOrEmpty())
                    _articlesList.value = articles

                else
                    _uiMessage.value = UIMessage(shouldDisplayNoArticlesFound = true)

            } catch (e: HttpException) {
                val sourcesResponse = e.response()?.errorBody()?.string()?.fromJson(
                    SourcesResponse::class.java
                )
                _uiMessage.value = UIMessage(
                    isLoading = false,
                    errorMessage = sourcesResponse?.message,
                    retryAction = {
                        getNewsBySource(sourceId)
                    })


            } catch (e: UnknownHostException) {

                _uiMessage.value = UIMessage(
                        isLoading = false,
                        errorMessageId = R.string.connection_error,
                        retryAction = {
                            getNewsBySource(sourceId)
                        })

            } catch (e: Exception) {
                _uiMessage.value = UIMessage(
                        isLoading = false,
                        errorMessage = e.localizedMessage,
                        retryAction = {
                            getNewsBySource(sourceId)
                        })

            }finally {
                hideErrorDialog()
            }
        }

    }
}
