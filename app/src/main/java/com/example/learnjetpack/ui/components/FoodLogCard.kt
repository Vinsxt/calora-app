package com.example.learnjetpack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnjetpack.model.FoodLog

@Composable
fun FoodLogCard(
    food: FoodLog,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Restaurant,
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = food.food_name,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "${food.protein.toInt()}P • ${food.carbs.toInt()}C • ${food.fat.toInt()}F",
                    style = MaterialTheme.typography.bodySmall
                )

            }

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    "${food.calories.toInt()} kcal"
                )

                TextButton(
                    onClick = onDelete
                ) {
                    Text("Delete")
                }

            }

        }

    }

}