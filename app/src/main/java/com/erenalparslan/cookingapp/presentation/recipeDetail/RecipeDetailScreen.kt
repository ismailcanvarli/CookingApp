package com.erenalparslan.cookingapp.presentation.recipeDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import com.erenalparslan.cookingapp.presentation.components.RecipeIntroductionsItem
import com.erenalparslan.cookingapp.presentation.components.RecipeMaterialItem
import com.erenalparslan.cookingapp.util.Screen

@Composable
fun RecipeDetailScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<RecipeDetailViewModel>()
    val state by viewModel.cookState.collectAsState()


    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    if (state.isError) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = "Sorry,an error occurred")
        }
    }


    state.cook?.let { cook ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                cook.recipeName?.let { TopBar(it, navHostController) }
            }
            item {
                RecipeImage(cook.imageUrl)
            }
            item {
                RecipeStats(cook)
            }
            item {
                RecipeMaterials(cook)
            }
            item {
                RecipeIntroductions(cook)
            }
            item {
                Button(
                    onClick = { navHostController.navigate(Screen.Making.route + "/${cook.id}") },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(20.dp)
                ) {
                    Text(text = "Tarifi Yapmaya Başla")
                }
            }
        }
    }


}

@Composable
fun TopBar(name: String, navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIos,
                    contentDescription = "Back"
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }


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
fun RecipeStats(recipe: CookDtoItem?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${recipe?.servesFor}", fontSize = 18.sp)
            Text(text = "Kişi Sayısı", fontSize = 10.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${recipe?.preparationTime}", fontSize = 18.sp)
            Text(text = "Hazırlanma Süresi", fontSize = 10.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${recipe?.totalTime}", fontSize = 18.sp)
            Text(text = "Pişirme Süresi", fontSize = 10.sp)
        }
    }
}

@Composable
fun RecipeMaterials(recipe: CookDtoItem?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Malzemeler")
        recipe?.ingredients?.let { ingredients ->
            Column {
                ingredients.forEach { ingredient ->
                    ingredient?.let {
                        RecipeMaterialItem(it)
                    }
                }
            }
        }
    }
}


@Composable
fun RecipeIntroductions(recipe: CookDtoItem?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Aşamalar")
        recipe?.instructions?.let { instructions ->
            // Burada LazyColumn yerine Column kullanıyoruz
            Column {
                instructions.forEach { instruction ->
                    instruction?.let {
                        RecipeIntroductionsItem(it)
                    }
                }
            }
        }
    }
}


//Todo bu kısım değiştirilecek
data class Recipe(
    val name: String? = null,
    val image: String? = null,
    val serves: Int? = null,
    val prepTime: Int? = null,
    val cookTime: Int? = null
)