package com.erenalparslan.cookingapp.presentation.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material.icons.rounded.Kitchen
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Timelapse
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import com.erenalparslan.cookingapp.util.Screen

@Composable
fun CookItem(
    cook: CookDtoItem,
    navHostController: NavHostController
) {
    // Create an AsyncImagePainter with the movie's backdrop image URL
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(cook.imageUrl)
            .size(Size.ORIGINAL)
            .build()
    ).state

    // Define a default color for the background gradient
    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    // Main container for the movie item
    Column(
        modifier = Modifier
            .height(240.dp) // Set the height to wrap the content
            .width(210.dp) // Set a fixed width for the item
            .padding(5.dp) // Apply padding around the item
            .clickable {
                navHostController.navigate(Screen.Details.route + "/${cook.id}")
            } // Navigate to the movie details screen on click
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.Black))
    ) {
        // Display an error icon if the image fails to load
        if (imageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Fill the width of the parent
                    .padding(6.dp) // Apply padding around the box
                    .height(125.dp) // Set a fixed height for the box
                    .clip(RoundedCornerShape(22.dp)) // Clip the corners to be rounded
                    .background(MaterialTheme.colorScheme.primaryContainer), // Set background color
                contentAlignment = Alignment.Center // Center the content inside the box
            ) {
                Icon(
                    modifier = Modifier.size(70.dp), // Set the size of the icon
                    imageVector = Icons.Rounded.ImageNotSupported, // Use a "not supported" image icon
                    contentDescription = cook.recipeName // Set content description for accessibility
                )
            }
        }

        // Display the image if it loads successfully
        if (imageState is AsyncImagePainter.State.Success) {
            // Update the dominant color based on the image's average color


            // Display the image
            Image(
                modifier = Modifier
                    .fillMaxWidth() // Fill the width of the parent
                    .padding(6.dp) // Apply padding around the image
                    .height(125.dp) // Set a fixed height for the image
                    .clip(RoundedCornerShape(10.dp)), // Clip the corners to be rounded
                painter = imageState.painter, // Use the painter from the image state
                contentDescription = cook.recipeName, // Set content description for accessibility
                contentScale = ContentScale.Crop // Crop the image to fill the bounds
            )
        }

        Spacer(modifier = Modifier.height(3.dp)) // Add vertical space

        // Display the movie title
        Text(
            modifier = Modifier.fillMaxWidth().padding(2.dp), // Apply horizontal padding
            text = cook.recipeName!!, // Set the text to the movie title
            color = Color.Black, // Set the text color to white
            fontSize = 12.sp, // Set the font size
            maxLines = 1, // Limit the text to one line
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Display the movie rating
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,// Fill the width of the parent
            verticalAlignment = Alignment.CenterVertically
            // Apply padding
        ) {

            Row(modifier = Modifier.weight(1f)) {
                Icon(
                    modifier = Modifier.size(20.dp), // Set the size of the icon
                    imageVector = Icons.Rounded.Star, // Use a "not supported" image icon
                    contentDescription = "star",
                    tint = Color.Red
                )
                Text(text = cook.likeCount.toString(), fontSize = 12.sp)
            }

            Row(modifier = Modifier.weight(1f).wrapContentHeight(),
                horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,
                ) {
                Icon(
                    modifier = Modifier.size(20.dp), // Set the size of the icon
                    imageVector = Icons.Rounded.Timelapse, // Use a "not supported" image icon
                    contentDescription = "time",
                    tint = Color.Cyan,
                )
                Text(text = cook.totalTime.toString(), fontSize = 12.sp, lineHeight = TextUnit.Unspecified, letterSpacing = TextUnit.Unspecified)

            }

            Row(modifier = Modifier.weight(1f)) {
                Icon(
                    modifier = Modifier.size(20.dp), // Set the size of the icon
                    imageVector = Icons.Rounded.Kitchen, // Use a "not supported" image icon
                    contentDescription = "kitchen",
                    tint = Color.Red
                )
                Text(text = cook.difficultyLevel.toString(), fontSize = 12.sp)
            }


        }
    }
}