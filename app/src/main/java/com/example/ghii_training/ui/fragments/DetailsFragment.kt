package com.example.ghii_training.ui.fragments

import android.util.Log
import android.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ghii_training.R
import com.example.ghii_training.data.local.repo.RepoEntity
import com.example.ghii_training.data.local.user.UserEntity
import com.example.ghii_training.domain.model.repo.Repo
import com.example.ghii_training.domain.model.user.User
import com.example.ghii_training.utils.toRepo
import com.example.ghii_training.utils.toUser
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.random.Random

@Composable
fun DetailsFragment(id: Int, viewModel: DetailsViewModel = hiltViewModel()) {
    Log.e("DetailsFragment", "id: $id")
    val user = viewModel.getUser(id).collectAsState(initial = UserEntity(
        avatar_url = null,
        bio= null,
    blog= null,
    company= null,
    created_at= null,
    email= null,
    events_url= null,
    followers= 0,
    following= 0,
     id= 1,
    location= null,
    login= null,
    name= null,
    public_repos= 0,
    repos_url= null,
    twitter_username= null,
    type= null,
    url= null
    )
    )


    val repos = viewModel.getUserRepos(user.value.toUser().login?: "unknown").collectAsState(initial = listOf(
        RepoEntity(
        allow_forking = null,
        archive_url = null,
        archived = null,
        assignees_url = null,
        created_at = null,
        default_branch = null,
        description = null,
        disabled = null,
        fork = null,
        forks = null,
        forks_count = null,
        full_name = null,
        id = null,
        language = null,
        name = null,
        open_issues = null,
        open_issues_count = null,
        pushed_at = null,
        size = null,
        updated_at = null,
        url = null,
        visibility = null,
        watchers = null,
        watchers_count = null,
            stargazers_count = null,
    )
    ))
    DetailsRoot(user.value.toUser(), repos.value.map {
        it.toRepo() })
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun DetailsRoot(user: User, repos: List<Repo>){
    TopAppBar(
        title = { },
        navigationIcon = { IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        } },

    )
    Column(modifier = Modifier.fillMaxSize()) {
        Profile(user)
        Repositories(repos)
    }
}

@Composable
fun Profile(user: User){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp, bottom = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        //Profile Picture
        Box(modifier = Modifier
            .size(120.dp)
            .background(color = MaterialTheme.colorScheme.tertiary, shape = CircleShape)){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatar_url)
                    .build(),
                contentDescription = "Profile Picture",
                placeholder = painterResource(id = R.drawable.placeholder),
                modifier = Modifier.clip(shape = CircleShape),
            )
        }

        //Name
        Text(
            text = user.name ?:"Unknown",
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
            )

        //Username
        Text(
            text = "@${user.login}",
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        //Following
        FollowingInfo(user.followers!!, user.following!!)


    }
}

@Composable
fun FollowingInfo(followers: Int, following: Int){
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = following.toString(), fontSize = 16.sp)
            Text(text = "Following", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = followers.toString(), fontSize = 16.sp)
            Text(text = "Followers", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun Repositories(repos: List<Repo>){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Repositories", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = repos.size.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)

    }
    LazyColumn(modifier = Modifier.fillMaxSize()){
        itemsIndexed(repos){ index, repo ->
            RepositoryItem(repo)
        }
    }
}

@Composable
fun RepositoryItem(repo: Repo){
    val randomColor = generateColorFromString(repo.language?: "Unknown")
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 10.dp),
        shape = MaterialTheme.shapes.small,
        ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)) {
            Row{
                if(repo.visibility != "public") Icon(imageVector = Icons.Outlined.Lock, contentDescription ="Lock")
                Text(
                    text = repo.name?:"Unknown",
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
            }
            Text(
                text = repo.description ?: "No description",
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 16.sp)

            Row(modifier = Modifier.padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Default.Star, contentDescription ="Star", tint = Color.Yellow, modifier = Modifier.size(14.dp))
                Text(
                    text = "${repo.stargazers_count} stars",
                    modifier = Modifier.padding(start = 5.dp, end = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)

                Icon(painter = painterResource(id = R.drawable.fork), contentDescription = "Fork", modifier = Modifier.size(14.dp))
                Text(
                    text = "${repo.forks_count} forks",
                    modifier = Modifier.padding(start = 5.dp, end = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
                Box(modifier = Modifier
                    .size(14.dp)
                    .background(color = randomColor, shape = CircleShape))
                Text(
                    text = repo.language ?: "No language",
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
            }
        }
    }
}

fun generateColorFromString(input: String): Color {
    // Generate a hash code from the input string
    val hashCode = input.hashCode().absoluteValue

    // Extract the RGB values from the hash code
    val red = (hashCode and 0xFF0000 shr 16) / 255f
    val green = (hashCode and 0x00FF00 shr 8) / 255f
    val blue = (hashCode and 0x0000FF) / 255f

    // Create and return the Color object
    return Color(red, green, blue)
}