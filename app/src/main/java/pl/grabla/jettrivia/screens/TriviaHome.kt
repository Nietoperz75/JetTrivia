package pl.grabla.jettrivia.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import pl.grabla.jettrivia.component.Questions

@Composable
fun TriviaHome(viewModel : QuestionsViewModel = hiltViewModel()){
    Questions(viewModel = viewModel)
}