package pl.grabla.jettrivia.component

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.grabla.jettrivia.screens.QuestionsViewModel
import pl.grabla.jettrivia.util.AppColors

@Composable
fun Questions(viewModel: QuestionsViewModel){
    val questions = viewModel.data.value.data?.toMutableList()
    if(viewModel.data.value.loading == true){
        CircularProgressIndicator()
        Log.d("Loading", "Questions:...Loading...")
    }else{
        Log.d("Loading", "Questions: Loading stopped")
        questions?.forEach { questionItem ->
            Log.d("Result", "Questions: ${questionItem.question}")
        }
    }
}

@Preview
@Composable
fun QuestionDisplay(){
    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize().padding(4.dp), color = AppColors.mDarkPurple) {

    }
}