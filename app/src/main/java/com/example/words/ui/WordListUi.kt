@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.words.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.words.data.words.Word


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordListUi(words: List<String>)  {
    Scaffold(
        topBar = {
           // MainTopBar()
                 },
        content = {
            WordsContent(
                words = words
            )       // 1
            { word ->
                Log.e(
                    "WordsContent",
                    "Selected: $word"
                )
            } // 2

        }
    )
}



fun getRandomString(length: Int) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

@Composable
private fun WordColumnItem(
    word: Word,
    onClick: () -> Unit,
) {
    Row(                                              // 1
        modifier = Modifier.clickable { onClick() },    // 2
    ) {
        Text(
            modifier = Modifier.padding(16.dp),           // 3
            text = word.value,                            // 4
        )
    }
}

@Composable
private fun WordsContent(
    words: List<String>,
    onSelected: (Word) -> Unit,
) {
    LazyColumn {              // 1
        items(words) { word ->  // 2
            WordColumnItem(     // 3
                word = Word(word)
            ) { onSelected(Word(word)) }
        }
    }
}




