package com.erenalparslan.cookingapp.presentation.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.erenalparslan.cookingapp.R

@Composable
fun UserProfileScreen() {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val recipes = List(10) { "https://example.com/image_$it.jpg" }
    val photoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri })
    val painter: Painter? = selectedImageUri?.let { rememberAsyncImagePainter(model = it) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Kullanıcı Adı", fontSize = 28.sp)
            Button(onClick = { /*TODO: Hesaptan çıkma butonu*/ }) {
                Text(text = "Çıkış Yap")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painter ?: painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profil Fotoğrafı",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    })

            // Açıklama kısmını profil fotoğrafının yanına taşıdık
            Text(
                text = "Açıklama Açıklama Açıklama Açıklama Açıklama Açıklama açıklama ",
                modifier = Modifier.padding(start = 16.dp),
                textAlign = TextAlign.Start
            )
        }

        // "Profil Paylaş" butonunu kaldırdık. Sadece "Düzenle" butonu kaldı.
        Button(
            onClick = { /*TODO: Düzenleme işlemi*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Düzenle")
        }

        LazyColumn(
            modifier = Modifier.padding(top = 8.dp),
        ) {
            items(recipes.chunked(3)) { rowRecipes ->
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    rowRecipes.forEachIndexed { index, recipe ->
                        Card(
                            modifier = Modifier.size(120.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = recipe),
                                contentDescription = "Tarif Görseli $index",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}