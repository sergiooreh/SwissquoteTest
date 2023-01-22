package com.example.swissquotetest.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.swissquotetest.R

@Composable
fun BankCard(
    lastFourDigits: String
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    ) {
        val (image, fourDots, cardNumber) = createRefs()

        Image(
            painter = painterResource(
                id = R.drawable.silver_credit_card
            ),
            contentDescription = "card",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.constrainAs(image) {
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom,
                        start = parent.start,
                        end = parent.end
                    )
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )
        Text(
            text = ". . . .",
            modifier = Modifier.constrainAs(fourDots) {
                start.linkTo(
                    anchor = parent.start, margin = 24.dp
                )
                bottom.linkTo(
                    anchor = parent.bottom, margin = 20.dp
                )
            },
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = lastFourDigits,
            modifier = Modifier.constrainAs(cardNumber) {
                start.linkTo(
                    anchor = fourDots.end, margin = 10.dp
                )
                bottom.linkTo(
                    anchor = parent.bottom, margin = 20.dp
                )
            },
            color = Color.White
        )
    }
}


@Preview
@Composable
fun BankCardPrev() {
    BankCard(lastFourDigits = "1456")
}