@file:OptIn(ExperimentalMaterial3Api::class)

package com.erenalparslan.cookingapp.presentation.addRecipe

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.erenalparslan.cookingapp.R

@Composable
fun AddRecipeScreen() {
    var recipeName by remember { mutableStateOf("") }
    var servingSize by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var howTo by remember { mutableStateOf("") }
    var prepTime by remember { mutableStateOf("") }
    var cookTime by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    // Malzemeler ve miktarları tutan liste
    var ingredientsList by remember { mutableStateOf(listOf<Pair<String, String>>()) }

    val photoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri })

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {

            item {
                Text(
                    text = "Tarif Ekle", fontSize = 24.sp, modifier = Modifier
                )

                // Seçilen görseli gösteren Box
                selectedImageUri?.let { uri ->
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(350.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedImageUri != null) {
                            AsyncImage(
                                model = selectedImageUri,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            // Placeholder görüntüsü
                            Image(
                                painter = painterResource(id = R.drawable.placeholder),
                                contentDescription = "Placeholder Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                Button(onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text("Görsel Seçiniz")
                }

                // "Tarif" title
                Text(
                    text = "Tarif", fontSize = 24.sp
                )

                // Recipe name input
                OutlinedTextField(value = recipeName,
                    onValueChange = { recipeName = it },
                    label = { Text("Tarif İsmi") },
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                )

                Row {
                    // Kaç kişilik olacağını belirten input
                    OutlinedTextField(value = servingSize,
                        onValueChange = {
                            if (it.toIntOrNull() != null && it.toInt() > 0) servingSize = it
                        },
                        label = { Text("Kaç kişilik") },
                        modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    // Kategori seçimi
                    OutlinedTextField(
                        value = category, onValueChange = { category = it },
                        label = { Text("Kategori") },
                        modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                    )
                }

                Row {
                    // Preparation time input
                    OutlinedTextField(value = prepTime,
                        onValueChange = { prepTime = it },
                        label = { Text("Hazırlık Süresi") },
                        modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    // Cooking time input
                    OutlinedTextField(value = cookTime,
                        onValueChange = { cookTime = it },
                        label = { Text("Pişirme Süresi") },
                        modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                // "Malzemeler" title
                Text(
                    text = "Malzemeler", fontSize = 24.sp
                )

                // Malzeme ve miktar girdileri
                ingredientsList.forEachIndexed { index, (ingredient, quantity) ->
                    var ingredientState by remember { mutableStateOf(ingredient) }
                    var quantityState by remember { mutableStateOf(quantity) }

                    Row {
                        OutlinedTextField(value = ingredientState,
                            onValueChange = { newValue ->
                                ingredientState = newValue
                                // Malzeme değerini güncelle
                                ingredientsList = ingredientsList.toMutableList().also {
                                    it[index] = ingredientState to it[index].second
                                }
                            },
                            label = { Text("Malzeme") },
                            modifier = Modifier.weight(1.5f).padding(horizontal = 4.dp)
                        )

                        OutlinedTextField(value = quantityState,
                            onValueChange = { newValue ->
                                quantityState = newValue
                                // Miktar değerini güncelle
                                ingredientsList = ingredientsList.toMutableList().also {
                                    it[index] = it[index].first to quantityState
                                }
                            },
                            label = { Text("Miktar") },
                            modifier = Modifier.weight(0.5f).padding(horizontal = 4.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }

                // Yeni malzeme ve miktar girdisi eklemek için buton
                Button(onClick = {
                    // Yeni bir malzeme ve miktar girdisi ekler
                    ingredientsList = ingredientsList + ("" to "")
                }) {
                    Text("Malzeme Ekle")
                }

                // "Nasıl Yapılır" title
                Text(text = "Nasıl Yapılır", fontSize = 24.sp)

                // How to make it input
                OutlinedTextField(value = howTo,
                    onValueChange = { howTo = it },
                    label = { Text("Nasıl Yapılır") },
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                )

                // Submit button
                Button(onClick = { /* TODO: Implement submission */ }) {
                    Text("Tarif Ekle")
                }
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