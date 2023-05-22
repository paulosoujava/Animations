package com.paulo.animations.others

import android.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 200.dp

data class BookModel(val title: String, val author: String, val pageCount: Int)
val DEFAULT_BOOKS = listOf(
    BookModel(
        title = "Tomorrow and Tomorrow and Tomorrow",
        author = "Gabrielle Zevin",
        pageCount = 416
    ),

    BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
)

@Composable
fun Book(modifier: Modifier = Modifier, model: BookModel) =
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(model.title)
            Text(model.author)
            Text("${model.pageCount} pages")
        }
    }


@Composable
private fun CollapsedTopBar(
    modifier: Modifier = Modifier,
    isCollapsed: Boolean
) {

    Box(
        modifier = modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .height(COLLAPSED_TOP_BAR_HEIGHT)
            .padding(16.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        AnimatedVisibility(visible = isCollapsed) {
            Text(
                text = "Library",
                color = androidx.compose.ui.graphics.Color.White,
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

@Composable
private fun ExpandedTopBar() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT - COLLAPSED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Library",
            color = androidx.compose.ui.graphics.Color.White,
            style = MaterialTheme.typography.h3,
        )
    }
}

@Composable
fun ScaffoldLibrary(books: List<BookModel> = DEFAULT_BOOKS) {
    val listState = rememberLazyListState()
    val isCollapsed: Boolean by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    Scaffold(
        topBar = {
            CollapsedTopBar(isCollapsed = isCollapsed)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            state = listState
        ) {
            item { ExpandedTopBar() }

            items(items = books) { book ->
                Book(model = book)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ShowList() {
    ScaffoldLibrary(books = DEFAULT_BOOKS)
}