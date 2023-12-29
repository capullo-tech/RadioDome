package tech.capullo.radio.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import control.json.ServerStatus
import tech.capullo.radio.ui.theme.RadioTheme
import tech.capullo.radio.viewmodels.RadioViewModel

@Composable
fun RadioApp(
    modifier: Modifier = Modifier,
    radioViewModel: RadioViewModel = viewModel()
) {
    val hostAddresses: List<String> by radioViewModel.hostAddresses.collectAsStateWithLifecycle()

    Surface(
        modifier, color = MaterialTheme.colorScheme.background
    ) {
        RadioMainScreen(
            deviceName = radioViewModel.getDeviceName(),
            hostAddresses = hostAddresses,
            snapclientsList = radioViewModel.snapClientsList
        )
    }
}

@Composable
fun RadioMainScreen(
    deviceName: String,
    hostAddresses: List<String>,
    snapclientsList: List<ServerStatus>
) {
    Column {
        Text("Radio Capullo")
        Text("Discoverable as: $deviceName")
        NetworkInterfacesInfo(hostAddresses = hostAddresses)
        SnapclientList(snapclientList = snapclientsList)
    }
}
@Composable
fun NetworkInterfacesInfo(hostAddresses: List<String>) {
    Column {
        hostAddresses.forEach { hostAddress -> Text(hostAddress) }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun RadioAppPreview() {
    RadioTheme {
        RadioMainScreen(
            deviceName = "Pixel 3a API 28",
            hostAddresses = listOf("192.168.0.109", "100.17.17.4"),
            snapclientsList = listOf()
        )
    }
}
