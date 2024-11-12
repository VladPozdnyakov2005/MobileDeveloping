package com.example.mobiledeveloping.ui_components

import android.graphics.BitmapFactory
import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appinfo.MainViewModel
import com.example.appinfo.ui.theme.BgTrans
import com.example.appinfo.ui.theme.MainRed
import com.example.mobiledeveloping.R
import com.example.utils.DrawerEvents
import com.example.utils.ListItem
import java.lang.reflect.Modifier


@Composable
fun MainListItem (
    mainViewModel: MainViewModel = hiltViewModel(),
    item: ListItem,
    onClick:(ListItem) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(5.dp)
            .clickable {
                onClick(item)
            },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, MainRed)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (image, text, favoriteButton) = createRefs()

            AssetImage(imageName = item.imageName,
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize()
                    .constrainAs(image){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                text = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MainRed)
                    .padding(10.dp)
                    .constrainAs(text){
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            IconButton(
                onClick = {
                    mainViewModel.insertItem(
                        item.copy(isFav = !item.isFav)
                    )

                },
                modifier = Modifier.constrainAs(favoriteButton)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
            ){
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = if(item.isFav) MainRed else Color.White,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(BgTrans)
                        .padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun AssetImage(imageName:String, contentDescription: String, modifier: Modifier){
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(imageName)
    val bitMap = BitmapFactory.decodeStream(inputStream)
    Image(
        bitmap = bitMap.asImageBitmap(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}