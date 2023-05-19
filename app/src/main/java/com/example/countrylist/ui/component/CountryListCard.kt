package com.example.countrylist.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.countrylist.model.CountryItem

@Composable
fun CountryListCard(country: CountryItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(8.dp)
            .clickable {
                       //TODO fare la navigazione verso details
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = country.flags.png,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.clip(shape = RectangleShape).width(100.dp),
                contentDescription = "${country.name.common} flag"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.name.common)
        }
    }
}