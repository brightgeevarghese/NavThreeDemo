package com.example.navthreedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navthreedemo.nav.AppNavGraph
import com.example.navthreedemo.ui.theme.NavThreeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavThreeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavGraph(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavThreeDemoTheme {
        AppNavGraph()
    }
}



//@Composable
//fun bs(modifier: Modifier = Modifier) {
//    // Create a back stack, specifying the key the app should start with
//    val backStack = rememberNavBackStack<AppNavKey>(AppNavKey.Home)
////    LaunchedEffect(Unit) {
////        if (backStack.size == 1) {
////            backStack.add(ProductList("123"))
////            backStack.add(ProductDetail("456"))
////        }
////    }
//    // Supply your back stack to a NavDisplay so it can reflect changes in the UI
//    NavDisplay(
//        backStack = backStack,
//        onBack = { backStack.removeLastOrNull() },
//
////        entryProvider = {
////            when (it) {
////                Home -> NavEntry(it) {
////                    Text("Home")
////                }
////                is ProductList -> NavEntry(
////                    key = it,
////                    metadata = mapOf("pane" to "list", "highlight" to true)
////                ) { Text("Product list") }
////                Settings -> NavEntry(it) { Text("Settings") }
////                is ProductDetail -> NavEntry(it) { Text("Product Detail") }
////                else -> error("Unknown key")
////            }
////        }
//        entryProvider = entryProvider {
//            entry<ProductList> { Text("Product list", modifier = Modifier.padding(100.dp)) }
//            entry<ProductDetail>(
//                metadata = mapOf("pane" to "detail", "highlight" to "true")
//            ) { Text("Product Detail", modifier = Modifier.padding(100.dp)) }
//            entry<Home> { Text("Home") }
//            entry<Settings> { Text("Settings") }
//        }
//
//    )
//    // ...more on this later...
//    // Push a key onto the back stack (navigate forward),
//    // the navigation library will reflect the change in state
////    backStack.add(ProductList("123"))
//    //Removes the last element from this mutable list and returns that removed element,
//    // or returns null if this list is empty.
////    backStack.removeLastOrNull()
//}