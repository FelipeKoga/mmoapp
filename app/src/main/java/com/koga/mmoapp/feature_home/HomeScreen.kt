package com.koga.mmoapp.feature_home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.koga.mmoapp.model.Game


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn {
            items(uiState.games) { game ->
                GameItem(game)
            }
        }
    }

}

@Composable
fun GameItem(game: Game) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = game.thumbnail,
            contentDescription = game.title,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = game.title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = game.description)
    }
}
