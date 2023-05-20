package com.example.countrylist.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.countrylist.screens.viewmodel.CountryDetailsScreenViewModel
import com.example.countrylist.ui.component.CountryHeader
import com.example.countrylist.ui.component.CountryInfo

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

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = countryName ?: "") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        )
    }) {
        Column(modifier = Modifier.padding(it)) {
            if (!viewModel.selectedCountry.value.data.isNullOrEmpty()) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        val country = viewModel.selectedCountry.value.data!!.first()
                        CountryHeader(country)
                        CountryInfo(country)

                    }
                }
            }
        }
    }
}