package com.dhia.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.Style
import com.dhia.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

// Mr Robot Color Scheme
val MrRobotBlack = Color(0xFF0D1117)
val MrRobotDarkGray = Color(0xFF161B22)
val MrRobotGray = Color(0xFF21262D)
val MrRobotGreen = Color(0xFF00FF41)
val MrRobotLightGreen = Color(0xFF39FF14)
val MrRobotRed = Color(0xFFFF4444)
val MrRobotWhite = Color(0xFFE6EDF3)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MrRobotBlack)
                ) { innerPadding ->
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
    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meters") }
    var outputUnit by remember{ mutableStateOf("Meters") }
    var inputExpanded by remember{ mutableStateOf(false) }
    var outputExpanded by remember{ mutableStateOf(false) }
    var factorTo by remember{ mutableStateOf(1.0) }
    var factorFrom by remember{ mutableStateOf(1.0) }

    fun dodo(){
        val inputVal = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputVal * (factorFrom/factorTo) * 100).roundToInt()/100.0
        outputValue = result.toString()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MrRobotBlack)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MrRobotDarkGray
                ),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "> UNIT_CONVERTER.EXE",
                        color = MrRobotGreen,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "SYSTEM READY",
                        color = MrRobotLightGreen,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            // Input Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MrRobotGray
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "INPUT_VALUE:",
                        color = MrRobotGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = inputValue,
                        onValueChange = {
                            inputValue = it
                            dodo()
                        },
                        label = {
                            Text(
                                text = "Enter value...",
                                color = MrRobotWhite.copy(alpha = 0.7f),
                                fontFamily = FontFamily.Monospace
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MrRobotGreen,
                            unfocusedBorderColor = MrRobotWhite.copy(alpha = 0.5f),
                            focusedTextColor = MrRobotWhite,
                            unfocusedTextColor = MrRobotWhite,
                            cursorColor = MrRobotGreen
                        ),
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp
                        ),
                        shape = RoundedCornerShape(4.dp)
                    )
                }
            }

            // Unit Selection Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Input Unit Selector
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MrRobotGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "FROM:",
                            color = MrRobotGreen,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Box {
                            Button(
                                onClick = { inputExpanded = !inputExpanded },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MrRobotDarkGray,
                                    contentColor = MrRobotWhite
                                ),
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier
                                    .border(1.dp, MrRobotGreen, RoundedCornerShape(4.dp))
                            ) {
                                Text(
                                    text = inputUnit,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 12.sp
                                )
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Arrow Down",
                                    tint = MrRobotGreen
                                )
                            }

                            DropdownMenu(
                                expanded = inputExpanded,
                                onDismissRequest = { inputExpanded = false },
                                modifier = Modifier.background(MrRobotDarkGray)
                            ) {
                                val units = listOf(
                                    "Centimeters" to 0.01,
                                    "Meters" to 1.0,
                                    "Feet" to 0.3048,
                                    "Milimeters" to 0.001
                                )

                                units.forEach { (unit, factor) ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "> $unit",
                                                color = MrRobotWhite,
                                                fontFamily = FontFamily.Monospace
                                            )
                                        },
                                        onClick = {
                                            inputExpanded = false
                                            inputUnit = unit
                                            factorFrom = factor
                                            dodo()
                                        },
                                        colors = MenuDefaults.itemColors(
                                            textColor = MrRobotWhite
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Output Unit Selector
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MrRobotGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "TO:",
                            color = MrRobotGreen,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Box {
                            Button(
                                onClick = { outputExpanded = !outputExpanded },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MrRobotDarkGray,
                                    contentColor = MrRobotWhite
                                ),
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier
                                    .border(1.dp, MrRobotGreen, RoundedCornerShape(4.dp))
                            ) {
                                Text(
                                    text = outputUnit,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 12.sp
                                )
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Arrow Down",
                                    tint = MrRobotGreen
                                )
                            }

                            DropdownMenu(
                                expanded = outputExpanded,
                                onDismissRequest = { outputExpanded = false },
                                modifier = Modifier.background(MrRobotDarkGray)
                            ) {
                                val units = listOf(
                                    "Centimeters" to 0.01,
                                    "Meters" to 1.0,
                                    "Feet" to 0.3048,
                                    "Milimeters" to 0.001
                                )

                                units.forEach { (unit, factor) ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "> $unit",
                                                color = MrRobotWhite,
                                                fontFamily = FontFamily.Monospace
                                            )
                                        },
                                        onClick = {
                                            outputExpanded = false
                                            outputUnit = unit
                                            factorTo = factor
                                            dodo()
                                        },
                                        colors = MenuDefaults.itemColors(
                                            textColor = MrRobotWhite
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Result Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MrRobotDarkGray
                ),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "OUTPUT_RESULT:",
                        color = MrRobotGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (outputValue.isNotEmpty()) "$outputValue $outputUnit" else "0.0 $outputUnit",
                        color = MrRobotLightGreen,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Terminal-style footer
        Text(
            text = "fsociety@converter:~$ ready",
            color = MrRobotGreen.copy(alpha = 0.7f),
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(modifier: Modifier = Modifier) {
    UnitConverter()
}