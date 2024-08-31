package com.example.todoapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.R
import com.example.todoapp.model.TaskDomain
import com.example.todoapp.viewmodel.TodoListViewModel

@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier,
    onAddTask: () -> Unit,
    onTaskClick: (TaskDomain) -> Unit,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val loadingState by viewModel.loadingState.collectAsState()
    val taskList by viewModel.taskList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTaskList()
    }

    TodoListContent(
        tasks = taskList,
        isLoading = loadingState,
        onAddTask = onAddTask,
        onEditMode = {},
        onTaskClick = onTaskClick,
        modifier = modifier,
    )

}

@Composable
fun TodoListContent(
    tasks: List<TaskDomain>,
    isLoading: Boolean,
    onAddTask: () -> Unit,
    onEditMode: () -> Unit,
    onTaskClick: (TaskDomain) -> Unit,
    modifier: Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = modifier.fillMaxWidth(),
                title = { Text(text = "Todolist") },
                actions = {
                    IconButton(onClick = onEditMode) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Edit mode"
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddTask,
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Add Task") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            } else if (tasks.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.todo_list_empty_message),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn {
                    items(tasks) { task ->
                        TaskItem(task = task, onClick = { onTaskClick(task) })
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: TaskDomain, onClick: (TaskDomain) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { onClick(task) }),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                StatusChip(completed = task.completed)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                color = colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null,
                    tint = colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = task.dueDate.ifEmpty { "期限なし" },
                    style = MaterialTheme.typography.bodySmall,
                    color = colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun StatusChip(completed: Boolean) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (completed) Color.Green else Color.Yellow
    ) {
        Text(
            text = if (completed) "完了" else "未完了",
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun PreviewTodoListScreen() {
    MaterialTheme {
        TodoListContent(
            tasks = listOf(
                TaskDomain(
                    id = "sadsf",
                    title = "Task 1",
                    completed = true,
                    description = "Description 1",
                    dueDate = "",
                    position = 0
                ),
                TaskDomain(
                    id = "sadsf",
                    title = "Task 2",
                    completed = true,
                    description = "Description 2",
                    dueDate = "2024/2/2",
                    position = 1
                ),
                TaskDomain(
                    id = "sadsf",
                    title = "Task 3",
                    completed = false,
                    description = "Description 3",
                    dueDate = "2025/12/12",
                    position = 2
                ),
            ),
            isLoading = false,
            onAddTask = {},
            onEditMode = {},
            onTaskClick = {},
            modifier = Modifier
        )
    }
}