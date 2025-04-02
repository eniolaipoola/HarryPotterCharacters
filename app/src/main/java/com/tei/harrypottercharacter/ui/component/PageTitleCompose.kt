package com.tei.harrypottercharacter.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tei.harrypottercharacter.R

@Composable
fun PageTitle(
    modifier: Modifier,
    backHandler: () -> Unit,
    pageTitle: String
) {
    // Top back button on details page
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_button),
            contentDescription = stringResource(R.string.back_button_text),
            tint = colorResource(R.color.primary_color),
            modifier = modifier
                .size(dimensionResource(R.dimen.icon_size))
                .clickable { backHandler() }
        )
        Spacer(modifier.width(50.dp))
        Text(
            text = pageTitle,
            fontSize = dimensionResource(R.dimen.text_size_large).value.sp,
            color = colorResource(id = R.color.primary_color)
        )
    }
}