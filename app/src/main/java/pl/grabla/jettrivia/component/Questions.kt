package pl.grabla.jettrivia.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.grabla.jettrivia.model.QuestionItem
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

//@Preview
@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionsViewModel,
    onNextClick: (Int) ->Unit
){
    val choisesState = remember(question){
        question.choices.toMutableList()
    }
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(4.dp), color = AppColors.mDarkPurple) {
        Column(modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start){
            QuestionTracker()
            DrawDottedLine(pathEffect = pathEffect )
            Column(){
                Text(text = "What's the meaning of all this?",
                    modifier = Modifier.padding(12.dp)
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f),
                    fontSize = 17.sp,
                    color = AppColors.mOffWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp)
                //choises
                choisesState.forEachIndexed { index, answerText ->
                    Row(modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .border()){

                    }
                }
            }
        }
    }
}

@Composable
fun QuestionTracker(counter:Int = 10, outOf: Int = 100){
        Text(text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(textIndent = TextIndent.None)){
                withStyle(style = SpanStyle(color = AppColors.mLightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp)){
                    append("Question $counter/")
                    withStyle(style = SpanStyle(color = AppColors.mLightGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp)){
                        append("$outOf")
                    }
                }
            }

        }, modifier = Modifier.padding(12.dp))
}

//@Preview
@Composable
fun DrawDottedLine(pathEffect: PathEffect){
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp),){
        drawLine(color = AppColors.mLightGray,
        start = Offset(0f, 0f),
            end = Offset(size.width, y = 0f),
            pathEffect = pathEffect
        )
    }
}