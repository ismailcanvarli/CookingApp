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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Giriş Yap", fontSize = 24.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = password,
            onValueChange = { password = it },
            label = { Text("Şifre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /*TODO: Handle login*/ }, modifier = Modifier.fillMaxWidth()) {
            Text("Giriş Yap")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { /*TODO: Handle register*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Hesap Oluştur")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) } // Şifreyi gösterme durumunu ekledik

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Kayıt Ol", fontSize = 24.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Ad") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Soyad") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = password,
            onValueChange = { password = it },
            label = { Text("Şifre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /*TODO: Handle registration*/ }, modifier = Modifier.fillMaxWidth()) {
            Text("Kayıt Ol")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { /*TODO: Handle login screen navigation*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Giriş Yap")
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

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen()
}