package com.example.taskit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskit.data.Task
import com.example.taskit.TaskViewModel
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: TaskViewModel,
    navController: NavController
){

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    if(id!= 0L){
        val task = viewModel.getATaskById(id).collectAsState(initial = Task( 0L,"" ,"" ))
        viewModel.taskTitleState = task.value.title
        viewModel.taskDescriptionState = task.value.description
    }else{
        viewModel.taskTitleState = ""
        viewModel.taskDescriptionState = ""
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {AppBarView(title =
    if(id != 0L) stringResource(id = R.string.update_task)
    else stringResource(id = R.string.add_task)
    ) {navController.navigateUp()}
    },

        ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            TaskTextField(label = "Title",
                value = viewModel.taskTitleState,
                onValueChanged = {
                    viewModel.onTaskTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            TaskTextField(label = "Description",
                value = viewModel.taskDescriptionState,
                onValueChanged = {
                    viewModel.onTaskDescriptionChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))
            Button( modifier = Modifier.fillMaxWidth().padding(8.dp),
                onClick={
                if(viewModel.taskTitleState.isNotEmpty() &&
                    viewModel.taskDescriptionState.isNotEmpty()){
                    if(id!= 0L){
                        //TODO UpdateWish
                        viewModel.updateTask(
                            Task(
                                id = id,
                                title = viewModel.taskTitleState.trim(),
                                description = viewModel.taskDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "task has been updated"
                    }else{
                        //TODO addWish
                        viewModel.addTask(
                            Task(
                                title = viewModel.taskTitleState.trim(),
                                description = viewModel.taskDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "task has been created"
                    }

                }else{
                    //Enter fields for wish to be created
                    snackMessage.value = "Enter fields to create a task"
                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }


            }){
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update)
                    else stringResource(
                        id = R.string.add
                    ),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }

}


@Composable
fun TaskTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            textColor = Color.DarkGray,
            // using our own colors in Res.Values.Color
            focusedBorderColor = colorResource(id = R.color.app_bar_color),
            unfocusedBorderColor = colorResource(id = R.color.app_bar_color),
            cursorColor = colorResource(id = R.color.app_bar_color),
            focusedLabelColor = colorResource(id = R.color.app_bar_color),
            unfocusedLabelColor = colorResource(id = R.color.app_bar_color),
        )


    )
}

@Preview
@Composable
fun TaskTestFieldPrev(){
    TaskTextField(label = "text", value = "text", onValueChanged = {})
}



