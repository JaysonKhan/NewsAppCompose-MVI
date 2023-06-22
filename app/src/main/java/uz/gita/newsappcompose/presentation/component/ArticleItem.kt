package uz.gita.dima.xabarlarqr.presenter.screens.home.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import uz.gita.newsappcompose.R
import uz.gita.newsappcompose.data.remote.response.Result
import uz.gita.newsappcompose.ui.theme.NewsAppComposeTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ArticleItem(
    item_news: Result,
    listener: ()->Unit,
//    delete: @Composable () -> Unit
) {
    Card(modifier = Modifier
        .padding(10.dp)
        .wrapContentHeight(Alignment.Top)) {
        var expended by rememberSaveable { mutableStateOf(false) }
        val extraPadding by animateDpAsState(
            if (expended) 48.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        Column(modifier = Modifier.padding(12.dp)) {
            val painter = rememberImagePainter(data = item_news.image_url, builder = {})
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item_news.image_url)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.imageplaceholder),
                error = painterResource(id = R.drawable.noimage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                )
            Column(
                modifier = Modifier
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = item_news.title!!,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                    IconButton(onClick = { expended = !expended }) {
                        Icon(
                            imageVector = if (!expended) Icons.Filled.ArrowDropDown else Icons.Filled.KeyboardArrowUp,
                            contentDescription = if (expended) {
                                stringResource(R.string.show_less)
                            } else {
                                stringResource(R.string.show_more)
                            }
                        )
                    }
                }
                if (expended) {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = item_news.description!!
                    )

                    Row(modifier = Modifier.padding(5.dp)) {
                        Text(text = "Source: ")
                        Text(text = item_news.source_id!!,modifier = Modifier.weight(1f))
                        Text(text = item_news.pubDate!!)
                    }
                }
            }
        }
    }
}