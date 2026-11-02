package com.example.firstapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random



data class Product(
    val id: Int,
    val name: String,
    val price: Int
)
class MainActivity : ComponentActivity() {
    private var totalSum = 0
    private var productSize = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartScreen()
        }
    }
    @Composable
    fun ShoppingCartScreen() {
        var products by remember {
            mutableStateOf(
                listOf(
                    Product(0, "Товар #1", 100),
                    Product(1, "Товар #2", 150),
                    Product(2, "Товар #3", 56)
                )
            )
        }
        Column {
            totalSum = 0
            productSize = 0
            for (product in products) {
                Text(text = product.name + " - " + product.price + " рублей")
                totalSum += product.price
                productSize++
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Товаров на сумму: $totalSum рублей")
            AddProductSection {
                products = products + Product(
                    id = products.size,
                    name = "Товар #${products.size + 1}",
                    price = Random.nextInt(0, 100)
                )
            }
            RemoveProductSection {
                totalSum -= products.last().price
                products = products.dropLast(1)
            }
        }
    }
    @Composable
    fun AddProductSection(
        onAdd: () -> Unit
    ) {
        // Если сумма заказа больше 500, то показываем сообщение о бесплатной доставке
        if (totalSum > 500) {
            Toast.makeText(LocalContext.current, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
        }
        Button(onClick = onAdd) {
            Text(text = "Добавить товар")
        }
    }
    @Composable
    fun RemoveProductSection(
        onRemove: () -> Unit
    ) {
        if (productSize != 0) {
            Button(onClick = onRemove) {
                Text(text = "Удалить товар")

            }
        }
    }
}




@Composable
fun Hello(name: String?) {
    Text(
        text = if (name != null) "Hello $name!" else "Имя не задано"
    )
}

@Composable
fun CustomButton() {
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        ),
        border = BorderStroke(2.dp, Color.Gray)
    ) {
        Text(
            text = "Нажми на меня",
            fontSize = 16.sp
        )
    }
}

@Composable
fun Kitten(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .statusBarsPadding()
                .height(400.dp)
                .width(400.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable._b3e49315e7711eea7cdca697c28bd51_upscaled),
            contentDescription = "123",
        )
    }
}

@Composable
fun StateScreen(
    modifier: Modifier = Modifier
) {
    var rememberState by remember { mutableIntStateOf(0) }
    OutlinedTextField(
        value = rememberState.toString(),
        onValueChange = { rememberState = it.toInt() },
        modifier = Modifier
    )
}

@Composable
fun CircleInBox(){
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(120.dp)
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF9C27B0)),
            contentDescription = "Orange Circle",
            painter = painterResource(R.drawable.circle)
        )
    }
}

@Composable
fun ContactCard(
    name: String,
    secondName: String,
    surname: String,
    phone: String,
    address: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "Имя: $name")
        Text(text = "Отчество: $secondName")
        Text(text = "Фамилия: $surname")
        Text(text = "Мобильный телефон: $phone")
        Text(text = "Адрес: $address", textAlign = TextAlign.Center)
    }
}

@Composable
fun ContactCardWithIcon(
    name: String,
    secondName: String,
    surname: String,
    phone: String,
    address: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Имя: $name")
            Text(text = "Отчество: $secondName")
            Text(text = "Фамилия: $surname")
            Text(text = "Мобильный телефон: $phone")
            Text(text = "Адрес: $address", textAlign = TextAlign.Center)
        }
        Image(
            modifier = Modifier
                .size(25.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable._b3e49315e7711eea7cdca697c28bd51_upscaled),
            contentDescription = "Card"
        )
    }
}

@Composable
fun ContactListColumn () {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ContactCard("Евгений", "Андреевич", "Лукашин", "+7 495 495 95 95", "г. Москва, 3-я улица Строителей, д. 25, кв. 12")
        ContactCard("Василий", "Егорович", "Кузякин", "--", "Ивановская область, дер. Крутово, д. 4")
        ContactCard("Людмила", "Прокофьевна", "Калугина", "+7 495 788 78 78", "Москва, Большая Никитская, д. 43, кв. 290")
    }
}

@Composable
fun WaterTracker() {
    var waterCount by remember { mutableIntStateOf(0) }
    var daysDrinkingWater1500 by remember { mutableIntStateOf(0) }
    val primaryColor: Color = Color.Blue
    val secondaryColor: Color = Color.Gray
    val buttonTextColor: Color = Color.White
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {

        Text(
            text = "$daysDrinkingWater1500 ${getDaysForm(daysDrinkingWater1500)} подряд, когда выпито >1500 мл",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = { waterCount += 250 }
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = {
                if (waterCount >= 1500) daysDrinkingWater1500 += 1 else daysDrinkingWater1500 = 0
                waterCount = 0
            }
        ) {
            Text("Завершить день")
        }
    }
}

fun getDaysForm(count: Int): String {
    val lastTwoDigits = count % 100
    val lastDigit = count % 10

    return when {
        lastTwoDigits in 11..14 -> "дней"
        lastDigit == 1 -> "день"
        lastDigit in 2..4 -> "дня"
        else -> "дней"
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowWaterTracker() {
    WaterTracker()
}


