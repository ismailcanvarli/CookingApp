package com.erenalparslan.cookingapp.presentation.recipeDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeDetailScreen(recipe: Recipe) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        recipe.name?.let { TopBar(it) }
        RecipeImage(recipe.image)
        RecipeStats(recipe)
        RecipeInfo(recipe)
    }
}

@Composable
fun TopBar(recipeName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* Handle back click */ }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = recipeName,
            fontSize = 24.sp, // Metnin boyutunu ayarlar
            fontWeight = FontWeight.Bold, // Metnin kalınlığını ayarlar
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun RecipeImage(image: String?) {
    Image(
        painter = rememberAsyncImagePainter(model = image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun RecipeStats(recipe: Recipe) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${recipe.serves}", fontSize = 20.sp)
            Text(text = "Kişi Sayısı", fontSize = 12.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${recipe.prepTime} Dakika", fontSize = 20.sp)
            Text(text = "Hazırlanma Süresi", fontSize = 12.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${recipe.cookTime} Dakika", fontSize = 20.sp)
            Text(text = "Pişirme Süresi", fontSize = 12.sp)
        }
    }
}

@Composable
fun RecipeInfo(recipe: Recipe) {
    // Display the rest of the recipe information
}

@Preview(showBackground = true)
@Composable
fun PreviewRecipeDetailScreen() {
    RecipeDetailScreen(
        recipe = Recipe(
            name = "Pancakes",
            image = "https://source.unsplash.com/800x600/?pancakes",
            serves = 4,
            prepTime = 10,
            cookTime = 20
        )
    )
}

//Todo bu kısım değiştirilecek
data class Recipe(
    val name: String? = null,
    val image: String? = null,
    val serves: Int? = null,
    val prepTime: Int? = null,
    val cookTime: Int? = null
)