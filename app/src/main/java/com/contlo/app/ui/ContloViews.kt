package com.contlo.app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.contlo.app.common.format
import com.contlo.app.ui.theme.ContloAppTheme
import java.util.Date

@Composable
fun ListOfPullRequests(prItems: List<PRUiModel>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items = prItems, key = { it.id }) {
            PullRequestItem(data = it)
        }
    }
}

@Composable
fun PullRequestItem(data: PRUiModel, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(1f)
            .border(border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary))
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Row(modifier = Modifier.fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                PROpenAndCloseView(
                    start = data.dateCreated.format(),
                    end = data.dateClosed.format(),
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }
            GitUserProfileView(
                modifier = Modifier.widthIn(min = 100.dp),
                username = data.userName,
                imageUrl = data.imageUrl,
            )
        }
    }
}

@Composable
fun GitUserProfileView(username: String, imageUrl: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = username,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = username,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(all = 2.dp)
        )
    }
}

@Composable
fun PROpenAndCloseView(start: String, end: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        LabeledTextView(label = "Raised : ", text = start)
        LabeledTextView(label = "Closed : ", text = end)
    }
}

@Composable
fun LabeledTextView(label: String, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(all = 2.dp)
                .alpha(0.7f),
        )
        Text(
            text = text,
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(all = 2.dp)
        )
    }
}


val mockPr = PRUiModel(
    id = "1",
    title = "Critial fix for accounts screen",
    imageUrl = "https://i.stack.imgur.com/Trj9n.jpg",
    userName = "iamskay",
    dateCreated = Date(System.currentTimeMillis()),
    dateClosed = Date(System.currentTimeMillis())
)


@Preview
@Composable
fun LabeledTextPreview() {
    ContloAppTheme {
        LabeledTextView(label = "created: ", text = "10-12-2009", modifier = Modifier)
    }
}

@Preview
@Composable
fun PrItemPreview() {
    ContloAppTheme {
        PullRequestItem(data = mockPr)
    }
}

@Preview
@Composable
fun UserProfilePreview() {
    ContloAppTheme {
        GitUserProfileView(
            username = "Sachin Tendulkar",
            imageUrl = "https://i.stack.imgur.com/Trj9n.jpg"
        )
    }
}
