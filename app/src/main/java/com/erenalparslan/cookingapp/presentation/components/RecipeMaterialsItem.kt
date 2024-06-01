package com.erenalparslan.cookingapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.erenalparslan.cookingapp.data.remote.response.Ingredient

@Composable
fun RecipeMaterialItem(ingredients: Ingredient){
    Surface {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly) {
            Text(text ="${ingredients.ingredient}" )
            Text(text ="${ingredients.amount}" )

        }
    }
}