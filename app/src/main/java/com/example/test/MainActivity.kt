package com.example.test

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.TestTheme
import com.example.test.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                GitStatisticsScreen(
                    activities = listOf(
                        Activity("Committed changes", 22, Icons.Filled.Refresh),
                        Activity("Comment count", 15, Icons.Filled.Create),
                        Activity("Merged pull requests", 8, Icons.Filled.KeyboardArrowUp),
                        Activity("Closed pull requests", 3, Icons.Filled.Close)
                    ),
                    onButtonClick = { Toast.makeText(this, "Well done!", Toast.LENGTH_SHORT).show() }
                )
            }
        }
    }
}

data class Activity(val title: String, val count: Int, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitStatisticsScreen(
    activities: List<Activity>,
    onButtonClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Display message")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val profileIcon: Painter = painterResource(id = R.drawable.man_person_icon)
                Image(
                    painter = profileIcon,
                    contentDescription = "Profile Icon",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Szymon Węgrzynek",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Git statistics",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Recent Activities",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            activities.forEach { activity ->
                ActivityItem(activity)
            }
        }
    }
}

@Composable
fun ActivityItem(activity: Activity) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = activity.icon,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = activity.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = activity.count.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GitStatisticsScreenPreview() {
    TestTheme {
        GitStatisticsScreen(
            activities = listOf(
                Activity("Committed changes", 22, Icons.Filled.Refresh),
                Activity("Comment count", 15, Icons.Filled.Create),
                Activity("Merged pull requests", 8, Icons.Filled.KeyboardArrowUp),
                Activity("Closed pull requests", 3, Icons.Filled.Close)
            ),
            onButtonClick = {}
        )
    }
}
