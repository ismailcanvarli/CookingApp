import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.erenalparslan.cookingapp.R
import com.erenalparslan.cookingapp.presentation.components.ButtonCard
import com.erenalparslan.cookingapp.util.Screen

@Composable
fun ProfileScreen(navHostController: NavHostController) {
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

            ButtonCard(text = "Email ile devam et", iconRes = R.drawable.ic_mail) {
                navHostController.navigate(Screen.Login.route)
            }
        }

    }
}
