package com.example.countrylist.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.countrylist.model.CountryItem
import java.util.stream.Collectors

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CountryInfo (country: CountryItem) {
    Column() {
        Text(
            text = "Capitals: ${
                country.capital.stream()
                    .collect(
                        Collectors.joining(", ")
                    )
            }"
        )
        Text(text = "Area: ${"%,d".format(country.area.toInt())} Km²")
        Text(text = "Population: ${"%,d".format(country.population)}")
    }
}