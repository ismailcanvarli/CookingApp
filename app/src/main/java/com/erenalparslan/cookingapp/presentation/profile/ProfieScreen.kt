import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.erenalparslan.cookingapp.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center, // Bu satırı değiştirdik
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Yemeğinizin ayrıntılarına daha kolay ulaşmak için giriş yapın ",
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 26.dp),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = TextUnit.Unspecified
            )
            ButtonCard(text = "Gmail ile devam et", iconRes = R.drawable.google) {

            }
            ButtonCard(text = "Email ile devam et", iconRes = R.drawable.ic_mail) {

            }
        }

    }
}

@Composable
fun ButtonCard(text: String, iconRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable { }) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null, // TODO: Set appropriate content description
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = text, fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun UserProfileScreen() {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    // TODO: Gerçek tarif görsellerini buraya koyun
    // Örnek bir tarif listesi oluşturun
    val recipes = List(10) { "https://example.com/image_$it.jpg" }
    // Profil fotoğrafı ve kullanıcı bilgileri
    val photoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri })
    // Profil fotoğrafı
    val painter: Painter? = selectedImageUri?.let { rememberAsyncImagePainter(model = it) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top, // Ekranın en üstünde başlamak için
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Kullanıcı adı ve ayarlar butonu
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp), // Üstte 16.dp boşluk bırak
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Kullanıcı Adı", fontSize = 28.sp)
            Button(onClick = { /*TODO: Hesaptan çıkma butonu*/ }) {
                Text(text = "Çıkış Yap")
            }
        }

        // Profil fotoğrafı ve kullanıcı bilgileri
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profil fotoğrafı
            Image(painter = painter ?: painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profil Fotoğrafı",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        ) // Profil fotoğrafına tıklanınca galeriyi aç
                    })

            // Kullanıcı bilgileri
            Row(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "10", fontSize = 20.sp) // TODO: Gerçek tarif sayısını buraya koyun
                    Text(text = "Tarif", fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(16.dp)) // Arada 16.dp boşluk bırak
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "100", fontSize = 20.sp
                    ) // TODO: Gerçek takipçi sayısını buraya koyun
                    Text(text = "Takipçi", fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(16.dp)) // Arada 16.dp boşluk bırak
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "50", fontSize = 20.sp
                    ) // TODO: Gerçek takip edilen sayısını buraya koyun
                    Text(text = "Takip", fontSize = 12.sp)
                }
            }
        }

        // Açıklama kısmı
        Text(
            text = "Açıklama Açıklama Açıklama Açıklama Açıklama Açıklama açıklama ", // TODO: Gerçek açıklamayı buraya koyun
            modifier = Modifier.padding(vertical = 16.dp), textAlign = TextAlign.Center
        )

        // Düzenle ve Profil Paylaş butonları
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { /*TODO: Düzenleme işlemi*/ },
                modifier = Modifier
                    .weight(1f) // Bu satırı ekledik
                    .padding(end = 8.dp) // Bu satırı ekledik
            ) {
                Text(text = "Düzenle")
            }
            Button(
                onClick = { /*TODO: Profil paylaşma işlemi*/ },
                modifier = Modifier
                    .weight(1f) // Bu satırı ekledik
                    .padding(start = 8.dp) // Bu satırı ekledik
            ) {
                Text(text = "Profil Paylaş")
            }
        }

        // Tariflerin görüntülendiği bölüm
        LazyColumn(
            modifier = Modifier.padding(top = 8.dp),
        ) { // Dıştaki LazyColumn
            items(recipes.chunked(3)) { rowRecipes -> // 'recipes' listesini 3'lü gruplara böl
                Row(horizontalArrangement = Arrangement.SpaceEvenly) { // İçteki Row
                    rowRecipes.forEachIndexed { index, recipe -> // Her bir tarif için
                        Card(
                            modifier = Modifier.size(120.dp)
                        ) {
                            // Tarif görseli
                            Image(
                                painter = rememberAsyncImagePainter(model = recipe), // 'recipe' gerçek tarif görseli olmalı
                                contentDescription = "Tarif Görseli $index", // Uygun bir içerik açıklaması ekleyin
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen()
}