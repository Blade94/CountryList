package com.example.countrylist.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.countrylist.screens.viewmodel.CountryDetailsScreenViewModel
import java.util.stream.Collectors

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailsScreen(
    navController: NavController,
    countryName: String?,
    viewModel: CountryDetailsScreenViewModel
) {
    if (!countryName.isNullOrBlank()) {
        Text(text = countryName)
        viewModel.getSelectedCountry(name = countryName)
    }

    /**
     * Dati da visualizzare:
     * capitale
     * area
     * popolazione
     */

    Scaffold() {
        Column(modifier = Modifier.padding(it)) {
            TopAppBar(
                title = { Text(text = countryName ?: "") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
            if (!viewModel.selectedCountry.value.data.isNullOrEmpty()) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = viewModel.selectedCountry.value.data!!.first().flags.png,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .clip(shape = RectangleShape)
                                    .width(100.dp),
                                contentDescription = "${countryName} flag"
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column() {
                                Text(
                                    text = viewModel.selectedCountry.value.data!!.first().name.common,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 33.sp
                                )
                                Text(text = viewModel.selectedCountry.value.data!!.first().name.official)
                            }
                        }
                        Column() {
                            Text(
                                text = "Capitals: ${
                                    viewModel.selectedCountry.value.data!!.first().capital.stream()
                                        .collect(
                                            Collectors.joining(", ")
                                        )
                                }"
                            )
                            Text(text = "Area: ${"%,d".format(viewModel.selectedCountry.value.data!!.first().area.toInt())} Km²")
                            Text(text = "Population: ${"%,d".format(viewModel.selectedCountry.value.data!!.first().population)}")
                        }
                    }
                }
            }
        }
    }
}