package com.easy.wallet.home.transactions.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easy.wallet.design.theme.ThemePreviews
import com.easy.wallet.design.ui.EasyWalletTheme
import com.easy.wallet.model.asset.AssetBalance
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.fullWidth
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.HorizontalLayout
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import java.math.BigDecimal
import kotlin.random.Random

@Composable
internal fun AmountHeaderView(
  modifier: Modifier = Modifier,
  assetBalance: AssetBalance,
  rate: BigDecimal = BigDecimal.ONE,
  trends: List<String>
) {
  val modelProducer = remember { CartesianChartModelProducer.build() }
  LaunchedEffect(Unit) {
    modelProducer.tryRunTransaction {
      lineSeries { series(trends.map { it.toFloat() }) }
    }
  }
  Box(
    modifier = modifier,
    contentAlignment = Alignment.TopStart
  ) {
    CartesianChartHost(
      rememberCartesianChart(
        rememberLineCartesianLayer(),
        startAxis = rememberStartAxis(
          label = null,
          axis = null,
          tick = null,
          guideline = null
        ),
        bottomAxis = rememberBottomAxis(
          label = null,
          axis = null,
          tick = null,
          guideline = null
        )
      ),
      modelProducer,
      horizontalLayout = HorizontalLayout.fullWidth()
    )
    Column(
      modifier = Modifier
        .padding(12.dp)
        .background(
          MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.5f),
          RoundedCornerShape(8.dp)
        ).padding(8.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "${assetBalance.balance} ${assetBalance.symbol}",
        style = MaterialTheme.typography.headlineLarge
      )
//            Text(text = "¥ ${balance.toBigDecimal().times(rate)}")
    }
  }
}

@ThemePreviews
@Composable
private fun AmountHeader_Preview() {
  EasyWalletTheme {
    Surface {
      AmountHeaderView(
        modifier = Modifier
          .fillMaxWidth()
          .height(260.dp),
        assetBalance = AssetBalance.mockForPreview,
        trends = List(8) { Random.nextInt(12).toString() }
      )
    }
  }
}
