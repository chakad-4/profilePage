package com.example.profilepage

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilepage.ui.theme.almond
import com.example.profilepage.ui.theme.orange
import com.example.profilepage.ui.theme.org2
import com.example.profilepage.ui.theme.pink
import com.example.profilepage.ui.theme.white

class MainActivity : ComponentActivity() {

    private lateinit var profile: SharedPreferences
    private val stateName = mutableStateOf("")
    private val statePhonNumber = mutableStateOf("")
    private val stateEmail = mutableStateOf("")
    private val stateDate = mutableStateOf("")
    private val stateGender = mutableStateListOf(false, false)
    private val stateError1 = mutableStateOf(false)
    private val stateError2 = mutableStateOf(false)
    private val stateError3 = mutableStateOf(false)
    private val stateError4 = mutableStateOf(false)
    private val stateErrorGender = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profile = getSharedPreferences("profile", MODE_PRIVATE)

        stateGender[0] = profile.getBoolean("man", false)
        stateGender[1] = profile.getBoolean("woman", false)
        stateEmail.value = profile.getString("email", "") !!
        stateName.value = profile.getString("name", "") !!
        statePhonNumber.value = profile.getString("phon", "") !!
        stateDate.value = profile.getString("date", "") !!


        enableEdgeToEdge()
        setContent {
            MainUI()
        }
    }

    @Composable
    fun MainUI() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = almond
                )
                .verticalScroll(rememberScrollState())
        ) {
            Header()
            SpacerHeight(20)
            Form()
            SpacerHeight(15)
            ButtonCustom()
            SpacerHeight(20)

        }
    }

    @Composable
    fun Header() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            orange,
                            pink
                        )
                    ),
                    shape = RoundedCornerShape(
                        bottomStart = 30.dp,
                        bottomEnd = 30.dp
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SpacerHeight(20)
                Text(
                    "پروفایل کاربری",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = white,
                    style = TextStyle(
                        textDirection = TextDirection.Rtl
                    )
                )
                SpacerHeight(20)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_edit),
                        "",
                        tint = white
                    )
                    Spacerwidth(45)
                    Image(
                        painter = painterResource(R.drawable.profile),
                        "",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(shape = RoundedCornerShape(45.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacerwidth(45)
                    Icon(
                        painter = painterResource(R.drawable.ic_camera),
                        "",
                        tint = white
                    )
                }
            }
        }
    }

    @Composable
    fun SpacerHeight(height: Int) {
        Spacer(Modifier.height(height = height.dp))
    }

    @Composable
    fun Spacerwidth(width: Int) {
        Spacer(Modifier.width(width = width.dp))
    }

    @Composable
    fun Form() {

        SpacerHeight(20)
        Text(
            "نام و نام خانوادگی:",
            style = TextStyle(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp)
        )
        SpacerHeight(20)

        TextFieldCustom(
            stateName,
            direction = TextDirection.Rtl,
            placeholder = "امیرعلی امامی نیا",
            isError = stateError1.value
        )
        if (stateError1.value) {
            ErrorText()
        }

        SpacerHeight(30)
        Text(
            "شماره همراه:",
            style = TextStyle(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp)
        )
        SpacerHeight(20)

        TextFieldCustom(
            statePhonNumber,
            placeholder = "09123456789",
            type = KeyboardType.Number,
            isError = stateError2.value
        )
        if (stateError2.value) {
            ErrorText()
        }

        SpacerHeight(30)
        Text(
            "ایمیل:",
            style = TextStyle(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp)
        )
        SpacerHeight(20)

        TextFieldCustom(
            stateEmail,
            placeholder = "example@gmail.com",
            type = KeyboardType.Email,
            isError = stateError3.value
        )
        if (stateError3.value) {
            ErrorText()
        }

        SpacerHeight(30)
        Text(
            "تاریخ تولد:",
            style = TextStyle(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp)
        )
        SpacerHeight(20)

        TextFieldCustom(
            stateDate,
            placeholder = "1404/4/4",
            type = KeyboardType.Text,
            isError = stateError4.value
        )
        if (stateError4.value) {
            ErrorText()
        }

        SpacerHeight(30)
        Text(
            "جنسیت:",
            style = TextStyle(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp)
        )
        SpacerHeight(10)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("مرد")
            RadioButton(
                selected = stateGender[0],
                onClick = {
                    stateGender[0] = true
                    stateGender[1] = false
                },
                modifier = Modifier.scale(0.85f),
                colors = RadioButtonDefaults.colors(selectedColor = orange)
            )

            Text("زن")
            RadioButton(
                selected = stateGender[1],
                onClick = {
                    stateGender[0] = false
                    stateGender[1] = true
                },
                modifier = Modifier.scale(0.85f),
                colors = RadioButtonDefaults.colors(selectedColor = orange)
            )
        }

        if (stateErrorGender.value) {
            ErrorText()
        }
    }


    @Composable

    fun TextFieldCustom(
        state: MutableState<String>,
        direction: TextDirection = TextDirection.Ltr,
        placeholder: String,
        type: KeyboardType = KeyboardType.Text,
        isError: Boolean = false
    ) {
        TextField(
            value = state.value,
            onValueChange = { input ->
                if (type == KeyboardType.Number) {
                    val digitsOnly = input.filter { it.isDigit() }
                    if (digitsOnly.length <= 11) {
                        state.value = digitsOnly
                    }
                } else {
                    state.value = input
                }
            },
            isError = isError,   // ← اضافه شد
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = white,
                unfocusedContainerColor = white,
                errorIndicatorColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorContainerColor = white,
                errorPlaceholderColor = Color.Red
            ),
            textStyle = TextStyle(textDirection = direction),
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    style = TextStyle(textDirection = direction)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = type)
        )
    }


    @Composable
    fun ButtonCustom() {

        Button(
            onClick = {

                stateError1.value = stateName.value.isBlank()
                stateError2.value = statePhonNumber.value.isBlank()
                stateError3.value = stateEmail.value.isBlank()
                stateError4.value = stateDate.value.isBlank()

                stateErrorGender.value = !stateGender[0] && !stateGender[1]

                val noError =
                    !stateError1.value &&
                            !stateError2.value &&
                            !stateError3.value &&
                            !stateError4.value &&
                            !stateErrorGender.value

                if (noError) {

                    profile.edit().apply {
                        putString("name", stateName.value)
                        putString("phon", statePhonNumber.value)
                        putString("email", stateEmail.value)
                        putString("date", stateDate.value)
                        putBoolean("man", stateGender[0])
                        putBoolean("woman", stateGender[1])
                        apply()
                    }

                    Toast.makeText(this@MainActivity, "ثبت شد", Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = org2),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
        ) {
            Text(text = "ثبت تغیرات", fontSize = 20.sp)
        }
    }

    @Composable
    fun ErrorText() {
        Text(
            text = "پر کردن این فیلد اجباری‌ست",
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 5.dp)
        )
    }


}


