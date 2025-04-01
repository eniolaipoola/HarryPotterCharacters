package com.tei.harrypottercharacter.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.tei.harrypottercharacter.R

@Composable
fun SearchTopBar(
    searchText: String,
    onSearchTextChanged : (String) -> Unit,
    onSearch : () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        OutlinedTextField(
            value = searchText,
            onValueChange = { input ->  onSearchTextChanged(input) },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(0.9f),
            placeholder = {
                Text(stringResource(R.string.search_placeholder_text))
            },
            shape = RoundedCornerShape(dimensionResource(R.dimen.rounded_shape_large)),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_text),
                    tint = colorResource(R.color.primary_color)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.white),
                unfocusedContainerColor = colorResource(R.color.white),
                cursorColor = colorResource(R.color.primary_color),
                focusedTrailingIconColor = colorResource(R.color.primary_color)
            ),
        )
    }
}