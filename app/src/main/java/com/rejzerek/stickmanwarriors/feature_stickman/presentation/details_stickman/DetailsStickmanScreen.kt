package com.rejzerek.stickmanwarriors.feature_stickman.presentation.details_stickman

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.StickmanClass
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.add_edit_stickman.AddEditStickmanViewModel
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.add_edit_stickman.getStickmanClassImageResource
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.util.Screen
import com.rejzerek.stickmanwarriors.ui.theme.Gray200
import com.rejzerek.stickmanwarriors.ui.theme.archerColor
import com.rejzerek.stickmanwarriors.ui.theme.mageColor
import com.rejzerek.stickmanwarriors.ui.theme.warriorColor

@Composable
fun DetailsStickmanScreen(
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            color = backgroundColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
                AttributeRow("Name", nameState)
                AttributeRow("Health", healthState.toString())
                AttributeRow("Damage", damageState.toString())
                AttributeRow("Defense", defenseState.toString())
                AttributeRow("Weapon Name", weaponNameState)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.AddEditStickmanScreen.route + "?stickmanId=${viewModel.currentStickmanId}")
                },
                modifier = Modifier
                    .size(150.dp, 60.dp)
            ) {
                Text(
                    text = "Edit",
                    fontSize = 15.sp
                )
            }
            Button(
                onClick = {
                    navController.navigateUp()
                },
                modifier = Modifier
                    .size(150.dp, 60.dp)
            ) {
                Text(
                    text = "Return",
                    fontSize = 15.sp
                )
            }
        }


    }
}

@Composable
fun AttributeRow(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Black)
            .padding(2.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Gray200)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.Black,
            fontSize = 14.sp
        )
        Text(
            text = value,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 5.dp),
            color = Color.Black,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


//@Preview
//@Composable
//fun DetailsStickmanScreenPreview() {
//    val navController = rememberNavController()
//    val viewModel = AddEditStickmanViewModel()
//
//    DetailsStickmanScreen(navController = navController, viewModel = viewModel)
//}

