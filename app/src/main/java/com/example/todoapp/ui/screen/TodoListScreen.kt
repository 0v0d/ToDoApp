package com.example.todoapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.R
import com.example.todoapp.data.repository.model.TaskDomain
import com.example.todoapp.ui.common.DismissibleItem
import com.example.todoapp.viewmodel.TodoListViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun TodoListScreen(
    onAddTask: () -> Unit,
    onTaskClick: (TaskDomain) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val loadingState by viewModel.loadingState.collectAsState()
    val taskList by viewModel.taskList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTaskList()
    }

    TodoListContent(
        tasks = taskList.toImmutableList(),
        isLoading = loadingState,
        onAddTask = onAddTask,
        onTaskClick = onTaskClick,
        modifier = modifier,
        onDismiss = { id ->
            viewModel.deleteTask(id)
        }
    )
}

@Composable
fun TodoListContent(
    tasks: ImmutableList<TaskDomain>,
    isLoading: Boolean,
    onAddTask: () -> Unit,
    onTaskClick: (TaskDomain) -> Unit,
    onDismiss: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(vertical = 8.dp),
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
            TaskList(
                tasks = tasks,
                onTaskClick = onTaskClick,
                onDismiss = onDismiss
            )
        }
        ExtendedFloatingActionButton(
            onClick = onAddTask,
            icon = { Icon(Icons.Default.Add, contentDescription = null) },
            text = { Text("Add Task") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        )
    }
}

@Composable
fun TaskList(
    tasks: ImmutableList<TaskDomain>,
    onTaskClick: (TaskDomain) -> Unit,
    onDismiss: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(tasks, key = { it.id }) { task ->
            DismissibleItem(
                item = task,
                onDismiss = onDismiss,
                idSelector = { it.id }
            ) {
                TaskItem(
                    task = task,
                    onClick = { onTaskClick(task) }
                )
            }
        }
    }
}

@Composable
fun TaskItem(
    task: TaskDomain,
    modifier: Modifier = Modifier,
    onClick: (TaskDomain) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = { onClick(task) }
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
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                StatusChip(completed = task.completed)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = task.dueDate.ifEmpty { "期限なし" },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun StatusChip(
    completed: Boolean,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = if (completed) Color.Green else Color.Yellow
    ) {
        Text(
            text = stringResource(
                if (completed) {
                    R.string.common_completed
                } else {
                    R.string.common_progressing
                }
            ),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )
    }
}
