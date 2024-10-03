package com.smb.jc06_catalogo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.smb.jc06_catalogo2.ui.theme.JC06Catalogo2Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC06Catalogo2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyTextFieldOutlined(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    JC06Catalogo2Theme {
        MyTextFieldOutlined(modifier = Modifier)
    }
}


/////////////////////// CheckboxListExample ///////////////////////////
//1. Envolvemos los parámetros del Checkbox en una clase de datos llamada Option
//2. Creamos una función componible llamada CheckboxList que reciba una lista de opciones y el título asociado
//3. Creamos una columna para desplegar verticalmente los elementos
//4. Mostramos un componente Text con el título
//5. Añadimos espacio entre el título y las casillas
//6. Invocamos la función de extensión forEach() desde la lista options con el fin de iterar sobre cada opción
//7. Utilizamos a LabelledCheckbox() para desplegar las casillas con su etiqueta correspondiente

data class Option( // 1
    var checked: Boolean,
    var onCheckedChange: (Boolean) -> Unit = {},
    val label: String,
    var enabled: Boolean = true
)

@Composable
fun CheckboxListExample(modifier: Modifier) { // EJECUTAR ESTA !!!!
    val ingredientes = listOf(
        "Tomate",
        "Cebolla",
        "Ketchup",
        "Mostaza",
        "Mayonesa",
        "Patatas",
        "Queso",
        "Pepinillos",
        "Bacon"
    )

    //El map recorre la lista de Strings y devuelve una lista de Options
    val options = ingredientes.map {
        var checked by rememberSaveable { mutableStateOf(false) }
        // este es el constructor de Option
        Option(
            checked = checked,
            onCheckedChange = { checked = it },
            // se podría entender mejor puesto así:
            // onCheckedChange = { myNewChecked -> checked.value = myNewChecked },
            label = it,
        )
    }
    CheckboxList(options = options, listTitle = "¿Como quieres la hamburguesa?")
}

@Composable
fun CheckboxList(options: List<Option>, listTitle: String) {// 2
    Column { // 3
        Text(listTitle, textAlign = TextAlign.Justify) // 4
        Spacer(Modifier.size(16.dp)) // 5
        options.forEach { option -> // 6
            LabelledCheckbox( // 7
                checked = option.checked,
                onCheckedChange = option.onCheckedChange,
                label = option.label,
                enabled = option.enabled
            )
        }
    }
}
//////////////////////////////////////////////////////////////////


@Composable
// Es como MyCheckBoxWithText pero pasando valores por parámetros
fun CheckboxLabelExample(modifier: Modifier) { // EJECUTAR ESTA !!!!
    val checked = remember { mutableStateOf(true) }
    LabelledCheckbox(
        checked = checked.value,
        onCheckedChange = { checked.value = it },
        label = "Checkbox con etiqueta"
    )
}

@Composable
fun LabelledCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Row(
        modifier = modifier.height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = colors
        )
        Spacer(Modifier.width(25.dp))
        Text(label)
    }
}


@Composable
fun MyCheckBoxWithText() {
    var state by rememberSaveable { mutableStateOf(true) }

    Row(
        modifier = Modifier.height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = state,
            onCheckedChange = { state = !state },
            enabled = true
        )
        Spacer(Modifier.width(15.dp))
        Text("label")
    }
}

@Composable
fun MyCheckBox(modifier: Modifier) {
    var state by rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Yellow,
            checkmarkColor = Color.Blue
        )
    )
}

@Composable
fun MySwitch(modifier: Modifier) {
    var state by rememberSaveable { mutableStateOf(true) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(
            checked = state,
            onCheckedChange = { state = !state },
            enabled = true,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.Red,
                uncheckedTrackColor = Color.Magenta,
                checkedThumbColor = Color.Green,
                checkedTrackColor = Color.Cyan,
                disabledCheckedTrackColor = Color.Yellow,
                disabledCheckedThumbColor = Color.Yellow,
                disabledUncheckedThumbColor = Color.Yellow,
                disabledUncheckedTrackColor = Color.Yellow
            )
        )
    }
}

@Composable
fun MyProgressAdvance(modifier: Modifier) {
    var progressStatus by rememberSaveable { mutableStateOf(0f) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = { progressStatus },
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(30.dp), horizontalArrangement = Arrangement.Center
        ) {
            ElevatedButton(onClick = { progressStatus += 0.1f }) {
                Text(text = "Sumar")
            }
            ElevatedButton(onClick = { progressStatus -= 0.1f }) {
                Text(text = "Restar")
            }
        }
    }
}

@Composable
fun MyProgress(modifier: Modifier) {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 8.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.Green
            )
        }

        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar perfil")
        }
    }
}

@Composable
fun MyIcon(modifier: Modifier) {

    // buscar iconos en https://fonts.google.com/icons
    // añadir en build.gradle.kts implementation androidx.compose.material:material-icons-extended
    // pero pesa mucho (34 Mb)
    Column(Modifier.fillMaxSize()) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Icono",
            tint = Color.Blue
        )
    }
}

@Composable
fun MyImageAdvance(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        modifier = Modifier
            //.clip(RoundedCornerShape(25f))
            .clip(CircleShape)
            //.border(2.dp, Color.Red)
            .border(2.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyImage(modifier: Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.movie11),
            contentDescription = "ejemplo",
            alpha = 0.7f
        )
    }
}

@Composable
fun MyButtonExample(modifier: Modifier) {
    // var enabled = true
    //var enabled by remember { mutableStateOf(true) }
    var enabled by rememberSaveable { mutableStateOf(true) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            //Log.i("Salva","Esto es un ejemplo"), meter en el onClick
            onClick = { enabled = false },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Red)
        ) {
            Text(text = "Enviar")
        }

        OutlinedButton(
            onClick = { enabled = false },
            enabled = enabled,
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Yellow,
                disabledContentColor = Color.Yellow
            )
        ) {
            Text(text = "Enviar")
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Enviar")
        }
    }
}

@Composable
fun MyTextFieldOutlined(modifier: Modifier) {
    var myText by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            value = myText,
            onValueChange = { myText = it },
            modifier = Modifier.padding(24.dp),
            label = { Text(text = "Holita") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Magenta,
                unfocusedBorderColor = Color.Blue
            )
        )
    }
}

@Composable
fun MyTextFieldAdvance(modifier: Modifier) {
    var myText by remember { mutableStateOf("") }

    TextField(
        value = myText,
        onValueChange = {
            myText = if (it.contains("a")) {
                it.replace("a", "")
            } else {
                it
            }
        },
        label = { Text(text = "Introduce tu nombre") })
}

@Composable
fun MyTextField(modifier: Modifier) {
    var myText by remember { mutableStateOf("Salva") }
    TextField(value = myText, onValueChange = { myText = it })
}

@Composable
fun MyText(modifier: Modifier) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        Text(text = "Esto es un ejemplo", fontFamily = FontFamily.Cursive)
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.combine(
                listOf(TextDecoration.Underline, TextDecoration.LineThrough)
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}
