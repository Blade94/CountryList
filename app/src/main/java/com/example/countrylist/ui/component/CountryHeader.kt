package com.example.countrylist.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.countrylist.model.CountryItem

@Composable
fun CountryHeader(country: CountryItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = country.flags.png,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(shape = RectangleShape)
                .width(100.dp),
            contentDescription = "${country.name} flag"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            Text(
                text = country.name.common,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 33.sp
            )
            Text(text = country.name.official)
        }
    }
}