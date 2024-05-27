@file:OptIn(ExperimentalMaterial3Api::class)

package com.erenalparslan.cookingapp.presentation.addRecipe

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddRecipeScreen() {
    var recipeName by remember { mutableStateOf("") }
    var servingSize by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var ingredient by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var howTo by remember { mutableStateOf("") }

    var prepTime by remember { mutableStateOf("") }
    var cookTime by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Tarif Ekle")

            // Image picker
            Button(onClick = { /* TODO: Implement image picking */ }) {
                Text("Görsel Seçiniz")
            }

            // "Tarif" title
            Text(text = "Tarif")

            // Recipe name input
            OutlinedTextField(value = recipeName,
                onValueChange = { recipeName = it },
                label = { Text("Tarif İsmi") })

            OutlinedTextField(value = servingSize,
                onValueChange = {
                    if (it.toIntOrNull() != null && it.toInt() > 0) servingSize = it
                },
                label = { Text("Kaç kişilik") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )


            // Preparation time input
            OutlinedTextField(value = prepTime,
                onValueChange = { prepTime = it },
                label = { Text("Hazırlık Süresi") })

            // Cooking time input
            OutlinedTextField(value = cookTime,
                onValueChange = { cookTime = it },
                label = { Text("Pişirme Süresi") })

            // Category selection
            OutlinedTextField(value = category,
                onValueChange = { category = it },
                label = { Text("Kategori") })

            // "Malzemeler" title
            Text(text = "Malzemeler")

            // Ingredient and quantity input
            Row {
                OutlinedTextField(value = ingredient,
                    onValueChange = { ingredient = it },
                    label = { Text("Malzeme") })

                //TODO buradaki miktar kısmının detayı öğrenilecek
                OutlinedTextField(value = quantity,
                    onValueChange = {
                        if (it.toIntOrNull() != null && it.toInt() > 0) quantity = it
                    },
                    label = { Text("Miktar") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            // "Nasıl Yapılır" title
            Text(text = "Nasıl Yapılır")

            // How to make it input
            OutlinedTextField(value = howTo,
                onValueChange = { howTo = it },
                label = { Text("Nasıl Yapılır") })

            // Submit button
            Button(onClick = { /* TODO: Implement submission */ }) {
                Text("Tarif Ekle")
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewAddRecipeScreen() {
    AddRecipeScreen()
}