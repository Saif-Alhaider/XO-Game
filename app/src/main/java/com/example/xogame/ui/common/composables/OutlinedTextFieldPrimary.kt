package com.example.xogame.ui.common.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme

@Composable
fun OutlinedTextFieldPrimary(modifier:Modifier=Modifier ,onValueChanged:(String)->Unit,placeHolder:String = "") {
    OutlinedTextField(
        value = "",
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = placeHolder,
                textAlign = TextAlign.Center,
                color = XOGameCustomColors.current.onBackground36,
                modifier = Modifier.fillMaxWidth()
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
        shape = RoundedCornerShape(32.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = XOGameCustomColors.current.primaryPink,
            unfocusedBorderColor = XOGameCustomColors.current.primaryPink,
        ),
        modifier = modifier
            .fillMaxWidth()
    )
}

@Preview()
@Composable
fun OutlinedButtonPrimaryPreview() {
    XOGameTheme {
        OutlinedTextFieldPrimary(onValueChanged = {}, placeHolder = "Enter your name")
    }
}