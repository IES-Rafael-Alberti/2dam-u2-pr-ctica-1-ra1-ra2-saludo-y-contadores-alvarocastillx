package com.acasloa946.saludoycontadores.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


/**
 * @author: Álvaro Castilla Loaiza
 * Función que pinta la pantalla principal y controla cuando se muestra el dialog
 */
@Composable
fun Practica1() {
    var show by remember { mutableStateOf(false) }
    var nametext by remember { mutableStateOf("") }
    var contAceptar by rememberSaveable { mutableStateOf(0) }
    var contCancelar by rememberSaveable { mutableStateOf(0) }
    var myVal by rememberSaveable { mutableStateOf("") }


    //Columna que esta compuesta por todos los elementos
    Column(
        modifier = Modifier.background(color = Color(0xFFf9f4ef)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Botón saludar
        Button(
            onClick = { show = true },
            modifier = Modifier
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF8c7851)),
        ) {
            Text(text = "Saludar")
        }
        Text(text = "Hola $nametext",
            color = Color(0xFF020826),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic)
        //Si show = true se muestra el dialog
        if (show) {
            MyDialog(
                contAceptar = contAceptar,
                contCancelar = contCancelar,
                onClickAceptar = {
                    contAceptar++
                    show = false
                },
                onClickCancelar = {
                    contCancelar++
                    show = false
                    nametext = ""
                },
                myVal = myVal,
                onClickLimpiar = {
                    myVal = ""
                },
                onValueChange = {
                    myVal = it
                },
                cambiarNameText = {
                    nametext = myVal.capitalize()
                })
        }
    }
}

@Composable
        /**
         * Función de Dialog
         * @param: contAceptar -> contador que guarda las veces que se ha pulsado aceptar
         * @param: contCancelar -> contador que guarda las veces que se ha pulsado cancelar
         * @param: onClickAceptar -> gestiona botón y contador de aceptar
         * @param: onClickCancelar -> gestiona botón y contador de cancelar
         * @param: myVal -> variable del outlinedtextfield
         * @param: onClickLimpiar -> función para limpiar outlinedtextfield
         * @param: onValueChange -> gestiona cuando se cambia el valor del outlinedtextfield
         * @param: cambiarNameText -> cambia nametext por el nombre introducido en Outlinedtextfield
         */
fun MyDialog(
    contAceptar: Int,
    contCancelar: Int,
    onClickAceptar : () -> Unit,
    onClickCancelar : () -> Unit,
    myVal : String,
    onClickLimpiar : () -> Unit,
    onValueChange: (String) -> Unit,
    cambiarNameText : () -> Unit

) {

//dialog
    Dialog(onDismissRequest = {  }) {
        Column(
            modifier = Modifier
                .background(Color(0xFF716040))
                .padding(top = 15.dp, bottom = 20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            TituloDialog(msjtitulo = "Configuración")
            ControlOutLined(myVal, onValueChange = onValueChange,cambiarNameText)
            //row con 2 botones
            Row {
                Button(
                    onClick = {
                        onClickAceptar()
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFf9f4ef))
                ) {
                    Text(text = "Aceptar ($contAceptar)",
                        color = Color.Black)
                }
                Button(
                    onClick = {
                        onClickCancelar()
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFf9f4ef))
                ) {
                    Text(text = "Cancelar ($contCancelar)",
                        color = Color.Black)
                }
            }
            //row con 1 boton
            Row {
                Button(
                    onClick = {
                        onClickLimpiar()
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFf9f4ef))
                ) {
                    Text(text = "Limpiar",
                        color = Color.Black)
                }
            }

        }
    }


}
/**
 * función para mostrar título del dialog
 * @param msjtitulo: string del título
 */

@Composable
fun TituloDialog(msjtitulo: String) {
    Text(
        msjtitulo,
        fontSize = 20.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}

/**
 * función para mostrar el outlinedtextfield
 * @param: myVal -> variable del outlinedtextfield
 * @param: onValueChange -> gestiona cuando se cambia el valor del outlinedtextfield
 * @param: cambiarNameText -> cambia nametext por el nombre introducido en Outlinedtextfield
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControlOutLined(
    myVal: String,
    onValueChange: (String) -> Unit,
    changeNameText: () -> Unit
)
{

    OutlinedTextField(
        onValueChange = {
            onValueChange(it)
        },
        value = myVal,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black
        ),
        modifier = Modifier.padding(bottom = 20.dp),
        label = { Text(text = "Introduzca su nombre") })
    changeNameText()

}
