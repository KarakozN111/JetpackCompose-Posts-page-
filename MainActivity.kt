package kz.singularity.jetpackcomposemost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.singularity.jetpackcomposemost.ui.theme.JetpackComposeMostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeMostTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = { StaticBottomNavigationBar() }
                    ) { innerPadding ->
                        PostList(
                            posts = generatePosts(15),
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

data class Post(val title: String, val content: String)

fun generatePosts(count: Int): List<Post> {
    val posts = mutableListOf<Post>()
    for (i in 1..count) {
        posts.add(Post(title = "Post Title", content = "Some text."))
    }
    return posts
}

@Composable
fun PostList(posts: List<Post>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        items(posts) { post ->
            PostCard(title = post.title, content = post.content)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PostCard(title: String, content: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun StaticBottomNavigationBar() {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { /* No action needed */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_posts),
                    contentDescription = "Posts"
                )
            }
            IconButton(onClick = { /* No action needed */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_photos),
                    contentDescription = "Photos"
                )
            }
            IconButton(onClick = { /* No action needed */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_groups),
                    contentDescription = "Groups"
                )
            }
            IconButton(onClick = { /* No action needed */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_profile),
                    contentDescription = "Profile"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeMostTheme {
        Scaffold(
            bottomBar = { StaticBottomNavigationBar() }
        ) { innerPadding ->
            PostList(
                posts = generatePosts(15),
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}










