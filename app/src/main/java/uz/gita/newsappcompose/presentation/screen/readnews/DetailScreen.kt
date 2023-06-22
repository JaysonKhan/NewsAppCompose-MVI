package uz.gita.newsappcompose.presentation.screen.readnews

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.newsappcompose.R
import uz.gita.newsappcompose.data.remote.response.Result
import uz.gita.newsappcompose.navigation.MyScreen

class DetailScreen(val article: Result) : MyScreen() {

    @Composable
    override fun Content() {
        val viewModel: DetailScreenContract.ViewModel = getViewModel<DetailScreenViewModel>()
        val uiState = viewModel.collectAsState().value
        DetailScreenContent(article,uiState,viewModel::onEventDispatcher)
    }
}

@Composable
fun DetailScreenContent(
    article: Result,
    uiState: DetailScreenContract.UIState,
    onEventDispatcher: (DetailScreenContract.Intent) -> Unit
) {

    val state = rememberScrollState()
    var save by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) { state.animateScrollTo(100) }

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(12.dp)
            .verticalScroll(state)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.image_url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.imageplaceholder),
            error = painterResource(id = R.drawable.noimage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = article.title.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
          /*  Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 50.dp,
                            color = Color.Gray
                        ),
                        onClick = {
                            save = if (save) {
                                onEventDispatcher.invoke(DetailScreenContract.Intent.Save(article))
                                false
                            } else {
                                onEventDispatcher.invoke(DetailScreenContract.Intent.Remove(article))
                                true
                            }

                        }
                    ),
//                painter = if (save) painterResource(id = R.drawable.bookmark) else painterResource(id = R.drawable.bookmark_border),
                contentDescription = ""
            )*/
        }
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = article.description.toString()
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(text = article.content.toString(), fontSize = 58.sp)

        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier.padding(5.dp)) {
            Text(text = "Source: ")
            Text(text = article.source_id.toString(), modifier = Modifier.weight(1f))
            Text(text = article.pubDate.toString())
        }
    }
}

/*
@Composable
fun DetailArticleItem(article: Article) {
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(12.dp)
            .verticalScroll(state)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.urlToImage)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.no_pictures),
            error = painterResource(id = R.drawable.noimage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = article.title,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 50.dp,
                            color = Color.Gray
                        ),
                        onClick = {

                        }
                    ),
                painter = painterResource(id = R.drawable.bookmark),
                contentDescription = ""

            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = article.description
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(text = article.content, fontSize = 58.sp)

        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier.padding(5.dp)) {
            Text(text = "Source: ")
            Text(text = article.source.name, modifier = Modifier.weight(1f))
            Text(text = article.publishedAt)
        }
    }


    */
/*Column(modifier = Modifier
        .padding(12.dp)
        .verticalScroll(rememberScrollState())) {
        Column(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.no_pictures),
                error = painterResource(id = R.drawable.noimage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = article.title,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = article.description
                )

                Text(text = article.content, fontSize = 28.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Row(modifier = Modifier.padding(5.dp)) {
                    Text(text = "Source: ")
                    Text(text = article.source.name, modifier = Modifier.weight(1f))
                    Text(text = article.publishedAt)
                }
            }
        }
    }*//*

}*/
