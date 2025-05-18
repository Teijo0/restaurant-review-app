package com.example.restaurantreviewapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantreviewapp.model.Restaurant
import com.example.restaurantreviewapp.R  // tuo R drawable näkyviin

@Composable
fun RestaurantItem(
    restaurant: Restaurant,
    modifier: Modifier = Modifier,
    onItemClick: (Restaurant) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(restaurant) }, // Klikattava Card
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = restaurant.imageUrl,
                contentDescription = "Restaurant Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(text = "Rating: ${restaurant.rating} (${restaurant.reviewCount})")

                Row {
                    val fullStars = restaurant.rating.toInt()
                    val hasHalfStar = (restaurant.rating - fullStars) >= 0.5

                    repeat(fullStars) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Full Star",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    if (hasHalfStar) {
                        Image(
                            painter = painterResource(id = R.drawable.star_half),
                            contentDescription = "Half Star",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Text(text = "Type: ${restaurant.type}")
                Text(text = "Price: ${restaurant.priceLevel}")
                Text(text = "Address: ${restaurant.address}")
                Text(
                    text = if (restaurant.isOpen) "Open" else "Closed",
                    color = if (restaurant.isOpen) Color.Green else Color.Red
                )
            }
        }
    }
}
