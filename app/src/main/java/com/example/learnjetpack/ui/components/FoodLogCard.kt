package com.example.learnjetpack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnjetpack.model.FoodLog

@Composable
fun FoodLogCard(
    food: FoodLog
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
                    text = food.meal_type,
                    style = MaterialTheme.typography.bodySmall
                )

            }

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    "${food.calories.toInt()} kcal"
                )

                Text(
                    "P ${food.protein.toInt()}g",
                    style = MaterialTheme.typography.bodySmall
                )

            }

        }

    }

}