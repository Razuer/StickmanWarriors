package com.rejzerek.stickmanwarriors.feature_stickman.presentation.add_edit_stickman

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rejzerek.stickmanwarriors.R
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.StickmanClass
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.util.Screen
import com.rejzerek.stickmanwarriors.ui.theme.archerColor
import com.rejzerek.stickmanwarriors.ui.theme.mageColor
import com.rejzerek.stickmanwarriors.ui.theme.warriorColor
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditStickmanScreen(
    navController: NavController,
    viewModel: AddEditStickmanViewModel = hiltViewModel()
) {
    val nameState = viewModel.stickmanName.value
    val healthState = viewModel.stickmanHealth.value
    val damageState = viewModel.stickmanAttackDamage.value
    val defenseState = viewModel.stickmanDefense.value
    val weaponNameState = viewModel.stickmanWeapon.value
    val classState = viewModel.stickmanClass.value

    val backgroundColor = when (classState) {
        StickmanClass.WARRIOR -> warriorColor
        StickmanClass.ARCHER -> archerColor
        StickmanClass.MAGE -> mageColor
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditStickmanViewModel.UiEvent.ShowSnackbar -> {
//                    Toast.makeText(navController.context, event.message, Toast.LENGTH_SHORT).show()
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditStickmanViewModel.UiEvent.SaveStickman -> {
                    if(navController.previousBackStackEntry?.destination?.route == Screen.StickmenScreen.route){
                        navController.navigateUp()
                    }else {
                        navController.navigateUp()
                        navController.navigateUp()
                    }
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(15.dp))
                    .background(backgroundColor)
                    .padding(20.dp)
            ) {

                Image(
                    painter = painterResource(id = getStickmanClassImageResource(classState)),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .background(Color.Black, CircleShape)
                        .size(120.dp)
                        .padding(3.dp)
                        .clip(CircleShape)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                TextField(
                    value = nameState,
                    onValueChange = {
                        viewModel.onEvent(AddEditStickmanEvent.EnteredName(it))
                    },
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    TextField(
                        value = healthState.toString(),
                        onValueChange = {
                            viewModel.onEvent(
                                AddEditStickmanEvent.EnteredHealth(
                                    it.takeIf { it.isNotEmpty() }?.toLongOrNull()
                                        ?.coerceAtMost(Int.MAX_VALUE.toLong())?.toInt() ?: 0
                                )
                            )
                        },
                        label = { Text("Health") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.outline_ecg_heart_24),
                                contentDescription = null
                            )
                        }
                    )
                    TextField(
                        value = defenseState.toString(),
                        onValueChange = {
                            viewModel.onEvent(
                                AddEditStickmanEvent.EnteredDefense(
                                    it.takeIf { it.isNotEmpty() }?.toLongOrNull()
                                        ?.coerceAtMost(Int.MAX_VALUE.toLong())?.toInt() ?: 0
                                )
                            )
                        },
                        label = { Text("Defense") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.outline_shield_24),
                                contentDescription = null
                            )
                        }
                    )

                }
                TextField(
                    value = damageState.toString(),
                    onValueChange = {
                        viewModel.onEvent(
                            AddEditStickmanEvent.EnteredAttackDamage(
                                it.takeIf { it.isNotEmpty() }?.toLongOrNull()
                                    ?.coerceAtMost(Int.MAX_VALUE.toLong())?.toInt() ?: 0
                            )
                        )

                    },
                    label = { Text("Damage") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    leadingIcon = {
                        Icon(
                            painterResource(id = R.drawable.outline_swords_24),
                            contentDescription = null
                        )
                    }
                )
                TextField(
                    value = weaponNameState,
                    onValueChange = {
                        viewModel.onEvent(AddEditStickmanEvent.EnteredWeapon(it))
                    },
                    label = { Text("Weapon Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                StickmanClassRadioButton(
                    stickmanClass = StickmanClass.WARRIOR,
                    currentClass = classState,
                    viewModel = viewModel,
                    iconResource = R.drawable.warrior_img,
                    backgroundColor = warriorColor,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                StickmanClassRadioButton(
                    stickmanClass = StickmanClass.ARCHER,
                    currentClass = classState,
                    viewModel = viewModel,
                    iconResource = R.drawable.archer_img,
                    backgroundColor = archerColor,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp)
                )
                StickmanClassRadioButton(
                    stickmanClass = StickmanClass.MAGE,
                    currentClass = classState,
                    viewModel = viewModel,
                    iconResource = R.drawable.mage_img,
                    backgroundColor = mageColor,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(AddEditStickmanEvent.SaveStickman)
                    },
                    modifier = Modifier
                        .size(150.dp, 60.dp)
                ) {
                    Text("Save")
                }
                Button(
                    onClick = {
                        navController.navigateUp()
                    },
                    modifier = Modifier
                        .size(150.dp, 60.dp)
                ) {
                    Text("Return")
                }
            }
        }
    }
}

@Composable
fun StickmanClassRadioButton(
    stickmanClass: StickmanClass,
    currentClass : StickmanClass,
    viewModel: AddEditStickmanViewModel,
    iconResource: Int,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                color = if (currentClass == stickmanClass) backgroundColor.copy(alpha = 0.8f) else Color.Transparent,
            )
            .clickable {
                viewModel.onEvent(
                    AddEditStickmanEvent.EnteredClass(
                        stickmanClass,
                        getStickmanClassImageResource(stickmanClass)
                    )
                )
            }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stickmanClass.name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

    }
}

fun getStickmanClassImageResource(stickmanClass: StickmanClass): Int {
    return when (stickmanClass) {
        StickmanClass.WARRIOR -> R.drawable.warrior_img
        StickmanClass.ARCHER -> R.drawable.archer_img
        StickmanClass.MAGE -> R.drawable.mage_img
    }
}

@Composable
fun EditableCaptionField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    label: String = "Caption",
    onValueChange: (String) -> Unit,
    maxLength: Int = 110
) {
    var textState by remember { mutableStateOf(text) }
    val lightBlue = Color(0xffd8e6ff)
    val blue = Color(0xff76a9ff)

    Column {
        Text(
            text = label,
            modifier = modifier,
            textAlign = TextAlign.Start,
            color = blue
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = lightBlue,
                unfocusedContainerColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                if (it.length <= maxLength) {
                    textState = it
                    onValueChange(it)
                }
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                if (textState.isNotEmpty()) {
                    IconButton(onClick = {
                        textState = ""
                        onValueChange("")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        )

        Text(
            text = "${textState.length} / $maxLength",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.End,
            color = blue
        )
    }
}

@Preview
@Composable
fun EditableCaptionFieldExample() {
    var caption by remember { mutableStateOf("") }

    EditableCaptionField(
        onValueChange = { caption = it },
        text = caption
    )
}




//@Preview(showSystemUi = true)
//@Composable
//fun DecoratedComposablePreview() {
//    AddEditStickmanScreen()
//}