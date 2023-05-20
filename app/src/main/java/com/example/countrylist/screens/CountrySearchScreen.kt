package com.example.countrylist.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countrylist.screens.viewmodel.CountriesScreenViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CountrySearchScreen(
    navController: NavController,
    countriesScreenViewModel: CountriesScreenViewModel
) {
    val textFieldState = remember {
        mutableStateOf("")
    }
    val validState = remember(textFieldState.value) {
        textFieldState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Search") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = textFieldState.value,
                label = {
                    Text(text = "Filter by language or continent")
                },
                onValueChange = { string ->
                    textFieldState.value = string
                },
                singleLine = true,
                keyboardActions = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    textFieldState.value.trim()
                    //TODO chiamare api di ricerca
                    keyboardController?.hide()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                )
            )
            //TODO modificare condizione su un'altro booleano che scatta al click su ricerca
            if (textFieldState.value.isNotEmpty()) {
                Text(text = "is not void!")
//                LazyColumn(modifier = Modifier.padding(it)) {
//                    items(items = countriesScreenViewModel.countries.value.data!!.toList()) { country ->
//                        CountryListCard(country = country) { countryName ->
//                            navController.navigate(CountriesScreens.CountryDetailsScreen.name + "/$countryName")
//                        }
//                    }
//                }
            }
        }
    }
}