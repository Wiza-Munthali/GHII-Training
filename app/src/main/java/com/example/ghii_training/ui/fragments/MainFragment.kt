package com.example.ghii_training.ui.fragments

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.example.ghii_training.R
import com.example.ghii_training.domain.model.user.User

@Composable
fun MainFragment(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    val users = viewModel.query.collectAsState(initial = emptyList())
    MainRoot(users.value, navController)
}

//@Preview(showBackground = true)
@Composable
fun MainRoot(users: List<User>, navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Greeting()
        UserList(users, navController)
    }
}


@Composable
fun Greeting() {
    Text(
        text = "Github Users",
        modifier = Modifier.fillMaxWidth(),
        fontSize = 24.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun UserList(users: List<User>, navController: NavController){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(items = users) { index, item ->
            UserItem(user = item, navController)
        }
    }
}

@Composable
fun UserItem(user: User, navController: NavController) {
    Row(modifier = Modifier.clickable(onClick = { navController.navigate("user_screen/${user.id}") })
        .fillMaxWidth()
        .padding(vertical = 10.dp)
        .height(60.dp)) {
        //Profile Picture
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(color = Color.Red, shape = RoundedCornerShape(5.dp))
        ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatar_url)
                    .build(),
                contentDescription = "Profile Picture",
                placeholder = painterResource(id = R.drawable.placeholder),
                modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)),
            )
        }

        //Info
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 10.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
            ) {
            Text(
                text = user.login.toString(),
                fontSize = 28.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = user.type.toString(),
                    modifier = Modifier.padding(end = 5.dp),
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                )
                Box(modifier = Modifier
                    .size(4.dp)
                    .background(color = MaterialTheme.colorScheme.tertiary, shape = CircleShape))

                Text(
                    text = "${user.public_repos} Repositories",
                    modifier = Modifier.padding(start = 5.dp),
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )

            }

        }

        //Arrow
        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterEnd) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Arrow")
        }
    }
}