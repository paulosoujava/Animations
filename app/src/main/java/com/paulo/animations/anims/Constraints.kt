package com.paulo.animations.anims

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.paulo.animations.R

import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun ShowConstraints() {
    val listOfItems = arrayListOf<Int>()
    for (i in 0..10) {
        listOfItems.add(R.drawable.baseline_home_24)
    }
    LazyColumn(
        contentPadding = PaddingValues(20.dp)
    ) {
        items(listOfItems) { item ->
            ListItemConstraintNotTogether(
                painter = painterResource(id = item),
                name = "name of a big character",
                date = Date(),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}
@Composable
fun ListItem(
    painter: Painter,
    name: String,
    date: Date,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ConstraintLayout {
                    val (dogId, nameTv, dateTv) = createRefs()
                    val format = SimpleDateFormat( "dd/MM/yyyy")
                    val time = format.format(date.time)

                    Image(painter = painter, contentDescription = "",
                        modifier = Modifier
                            .background(Color.Red)
                            .constrainAs(dogId) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                height = Dimension.value(40.dp)
                                width = Dimension.value(40.dp)
                            })
                    Text(text = name,
                        modifier = Modifier.constrainAs(nameTv) {
                            top.linkTo(dogId.top)
                            start.linkTo(dogId.end, margin = 10.dp)
                        },
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                    Text(text = "Created at: $time",
                        modifier = Modifier.constrainAs(dateTv) {
                            top.linkTo(nameTv.bottom)
                            start.linkTo(dogId.end, margin = 10.dp)
                        },
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }

        }
    }
}



@Composable
fun ListItemConstraintNotTogether(
    painter: Painter,
    name: String,
    date: Date,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                val myConstrantSet =  ConstraintSet {
                    val dogId = createRefFor("dogId")
                    val nameTv = createRefFor("nameTv")
                    val dateTv = createRefFor("dateTv")
                    val bottomGuideLine = createGuidelineFromBottom(0.4f)

                    val favorite = createRefFor("fav")
                    val share = createRefFor( "share")

                    constrain(dogId){
                        top.linkTo(parent.top)
                        bottom.linkTo(bottomGuideLine)
                        start.linkTo(parent.start)
                        height = Dimension.value(40.dp)
                        width = Dimension.value(40.dp)
                    }
                    constrain(nameTv){
                        top.linkTo(dogId.top)
                        start.linkTo(dogId.end, margin = 10.dp)
                        bottom.linkTo(dateTv.top)
                    }
                    constrain(dateTv){
                        top.linkTo(nameTv.bottom)
                        start.linkTo(dogId.end, margin = 10.dp)
                        bottom.linkTo(bottomGuideLine)
                    }
                    constrain(favorite){
                        top.linkTo(bottomGuideLine, margin = 10.dp)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 20.dp)
                        end.linkTo(share.start)
                        height = Dimension.wrapContent
                        width = Dimension.value(24.dp)
                    }
                    constrain(share){
                        top.linkTo(bottomGuideLine, margin = 10.dp)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(favorite.end)
                        end.linkTo(parent.end, margin = 20.dp)
                        height = Dimension.wrapContent
                        width = Dimension.value(24.dp)
                    }
                    createHorizontalChain(favorite, share, chainStyle = ChainStyle.Packed)
                }
                ConstraintLayout(constraintSet = myConstrantSet) {


                    val format = SimpleDateFormat( "dd/MM/yyyy")
                    val time = format.format(date.time)

                    Image(painter = painter, contentDescription = "",
                        modifier = Modifier
                            .background(Color.Green)
                            .layoutId("dogId"))
                    Text(text = name,
                        modifier = Modifier.layoutId("nameTv"),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                    Text(text = "Created at: $time",
                        modifier = Modifier.layoutId("dateTv"),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                    Icon(Icons.Default.Favorite, contentDescription = null, modifier = Modifier.layoutId("fav"),)
                    Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.layoutId("share"),)
                }
            }

        }
    }
}
