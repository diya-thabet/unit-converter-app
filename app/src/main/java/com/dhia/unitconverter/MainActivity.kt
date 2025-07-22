package com.dhia.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhia.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
    Column(
        // the design code is this
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter",

        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField("", {})
        Spacer(modifier = Modifier.height(16.dp))
        Row {


            Box{
                Button(onClick = {}) {
                    Text("Select")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                // drop down menu
                DropdownMenu(expanded = false, onDismissRequest = {}) {
                    DropdownMenuItem(
                        text ={ Text(text = "Centimeters") },
                        onClick = {}
                    )
                    DropdownMenuItem(
                        text ={ Text(text = "Meters") },
                        onClick = {}
                    )
                    DropdownMenuItem(
                        text ={ Text(text = "Feet") },
                        onClick = {}
                    )
                    DropdownMenuItem(
                        text ={ Text(text = "Milimeters") },
                        onClick = {}
                    )
                }
            }
                Spacer(modifier = Modifier.width(27.dp))
                Box{
                    Button(onClick = {}) {
                        Text("Select")
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down"
                        )
                    }
                    // drop down menu
                    DropdownMenu(expanded = false, onDismissRequest = {}) {
                        DropdownMenuItem(
                            text ={ Text(text = "Centimeters") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text ={ Text(text = "Meters") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text ={ Text(text = "Feet") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text ={ Text(text = "Milimeters") },
                            onClick = {}
                        )
                    }
                }
            }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ")
        }

    }



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(modifier: Modifier = Modifier) {
    UnitConverter()
}