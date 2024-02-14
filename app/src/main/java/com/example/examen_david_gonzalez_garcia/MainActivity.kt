package com.example.examen_david_gonzalez_garcia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examen1viewmodel.data.Asignatura
import com.example.examen1viewmodel.data.DataSource
import com.example.examen_david_gonzalez_garcia.ui.theme.Examen_David_Gonzalez_GarciaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Examen_David_Gonzalez_GarciaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }
}

@Composable
fun Principal () {
    Main()
}

@Composable
fun Main() {
    // Variables
    var listaAsignaturas by remember { mutableStateOf( DataSource.loterias ) }
    var horasAContarOEliminar by remember { mutableStateOf( "" ) }
    var horasAContarOEliminarSet by remember { mutableStateOf(0) }
    var asignaturaGlo by remember { mutableStateOf( "" ) }
    var costoHoraGlo by remember { mutableStateOf( 0) }

    var añadir by remember { mutableStateOf(false) }

    var resumen by remember { mutableStateOf("") }

    var historia by remember { mutableStateOf("") }
    var lengua by remember { mutableStateOf("") }
    var ingles by remember { mutableStateOf("") }
    var sociales by remember { mutableStateOf("") }
    var biologia by remember { mutableStateOf("") }
    var fisica by remember { mutableStateOf("") }
    var quimica by remember { mutableStateOf("") }
    var todo by remember { mutableStateOf("") }

    var listaResumen by remember { mutableStateOf(mutableListOf<Asignatura>()) }

    Column (modifier = Modifier.fillMaxWidth()) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)) {
            Text(text = "Bienvenido a la academia de Sergio / DGG", modifier = Modifier.padding(start = 8.dp, top = 8.dp))
        }

        Row (modifier = Modifier
            .fillMaxWidth()) {

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)) {

                items(listaAsignaturas){ asignatura ->

                    Card (modifier = Modifier
                        .width(180.dp)
                        .padding(8.dp)) {
                        Column {
                            Row (modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Yellow)) {
                                Text(text = "Asig: ${asignatura.nombre}", modifier = Modifier.padding(8.dp))
                            }
                            Row (modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Cyan)) {
                                Text(text = "€/hora: ${asignatura.precioHora}", modifier = Modifier.padding(8.dp))
                            }
                            Row () {
                                Button(onClick = {
                                    horasAContarOEliminarSet = horasAContarOEliminar.toInt();
                                    asignatura.numeroHoras += horasAContarOEliminarSet;
                                    asignaturaGlo = asignatura.nombre;
                                    costoHoraGlo = asignatura.precioHora;
                                    horasAContarOEliminar = "";

                                    añadir = true;



                                    listaResumen.add(Asignatura(asignatura.nombre, asignatura.precioHora, asignatura.numeroHoras))
                                    resumen += "Asig: ${asignatura.nombre} precio hora: ${asignatura.precioHora} total horas ${asignatura.numeroHoras} \n";

                                                 }, modifier = Modifier.padding(8.dp)) {
                                    Text(text = "+")
                                }
                                Button(onClick = {
                                    horasAContarOEliminarSet = horasAContarOEliminar.toInt();
                                    asignatura.numeroHoras -= horasAContarOEliminarSet;

                                    if (asignatura.numeroHoras < 0){
                                        asignatura.numeroHoras = 0;
                                    }

                                    asignaturaGlo = asignatura.nombre;
                                    costoHoraGlo = asignatura.precioHora;
                                    horasAContarOEliminar = "";

                                    añadir = false;

                                    listaResumen.add(Asignatura(asignatura.nombre, asignatura.precioHora, asignatura.numeroHoras))
                                    resumen += "Asig: ${asignatura.nombre} precio hora: ${asignatura.precioHora} total horas ${asignatura.numeroHoras} \n";

                                }, modifier = Modifier.padding(8.dp)) {
                                    Text(text = "-")
                                }
                            }
                        }
                    }
                }
            }
        }
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            TextField(
                label = { Text(text = "Horas a contar o eliminar")},
                value = horasAContarOEliminar,
                onValueChange = {horasAContarOEliminar = it},
                modifier = Modifier
                    .width(350.dp)
                    .padding(8.dp)
            )
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray)) {
            Column (modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp)) {
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Magenta)) {

                    Column {
                        Text(text = "Última acción:")
                        if (añadir){
                            Text(text = "Se han añadido $horasAContarOEliminarSet horas de $asignaturaGlo y con $costoHoraGlo € / hora")
                        } else {
                            Text(text = "Se han eliminado $horasAContarOEliminarSet horas de $asignaturaGlo y con $costoHoraGlo € / hora")
                        }
                    }
                }
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.White)) {

                    Column {
                        Text(text = "Resumen: ")
                        //Text(text = resumen)
                        Text(text = todo)

                        for (asig in listaResumen){
                            when(asig.nombre){
                                "Historia" ->{
                                    historia = "Asig: ${asig.nombre} precio hora: ${asig.precioHora} total horas ${asig.numeroHoras} \n";
                                }
                                "Lengua" ->{
                                    lengua = "Asig: ${asig.nombre} precio hora: ${asig.precioHora} total horas ${asig.numeroHoras} \n";
                                }
                                "Ingles" -> {
                                    ingles = "Asig: ${asig.nombre} precio hora: ${asig.precioHora} total horas ${asig.numeroHoras} \n";
                                }
                                "Sociales" -> {
                                    sociales = "Asig: ${asig.nombre} precio hora: ${asig.precioHora} total horas ${asig.numeroHoras} \n";
                                }
                                "Biologia" -> {
                                    biologia = "Asig: ${asig.nombre} precio hora: ${asig.precioHora} total horas ${asig.numeroHoras} \n";
                                }
                                "Fisica" -> {
                                    fisica = "Asig: ${asig.nombre} precio hora: ${asig.precioHora} total horas ${asig.numeroHoras} \n";
                                }
                                "Quimica" -> {
                                    quimica = "Asig: ${asig.nombre} precio hora: ${asig.precioHora} total horas ${asig.numeroHoras} \n";
                                }
                            }
                        }

                        todo = historia+lengua+ingles+sociales+biologia+fisica+quimica;

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Examen_David_Gonzalez_GarciaTheme {
        Principal()
    }
}