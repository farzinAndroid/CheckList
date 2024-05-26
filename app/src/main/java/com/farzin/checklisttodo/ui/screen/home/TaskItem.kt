package com.farzin.checklisttodo.ui.screen.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.model.home.Subtask
import com.farzin.checklisttodo.model.home.Task
import com.farzin.checklisttodo.ui.components.MySpacerHeight
import com.farzin.checklisttodo.ui.components.MySpacerWidth
import com.farzin.checklisttodo.ui.theme.CheckListTheme
import com.farzin.checklisttodo.ui.theme.darkText
import com.farzin.checklisttodo.ui.theme.highPriority
import com.farzin.checklisttodo.ui.theme.lowPriority
import com.farzin.checklisttodo.ui.theme.mainBackground
import com.farzin.checklisttodo.ui.theme.mediumPriority
import com.farzin.checklisttodo.ui.theme.softgray
import com.farzin.checklisttodo.ui.theme.veryExtraSmall
import com.farzin.checklisttodo.utils.DigitHelper
import com.hitanshudhawan.circularprogressbar.CircularProgressBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    onCardClicked: () -> Unit,

) {


    var progress by remember {
        mutableFloatStateOf(0f)
    }

    LaunchedEffect(task.subTask) {
        val completedSubtasks = task.subTask.count { it.subtaskCompleted }
        val totalSubtasks = task.subTask.size
        progress = if (totalSubtasks > 0) {
            completedSubtasks.toFloat() / totalSubtasks * 100f
        } else {
            0f
        }
    }


    val animateProgress by animateFloatAsState(progress, label = "")

    val color = when (task.priority) {
        1 -> {
            MaterialTheme.colorScheme.highPriority
        }

        2 -> {
            MaterialTheme.colorScheme.mediumPriority
        }

        3 -> {
            MaterialTheme.colorScheme.lowPriority
        }

        else -> {
            Color.Gray
        }
    }



    Card(
        modifier = modifier
            .padding(vertical = 18.dp)
            .fillMaxWidth()
            .height(92.dp)
            .padding(horizontal = 16.dp)
            .shadow(
                6.dp,
                shape = Shapes().extraLarge,
                spotColor = MaterialTheme.colorScheme.darkText
            ),
        shape = Shapes().extraLarge,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.mainBackground),
        onClick = { onCardClicked() }
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(0.9f)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressBar(
                        progress = animateProgress,
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                width = 0.dp,
                                color = Color.Transparent,
                                shape = CircleShape
                            ),
                        progressMax = 100f,
                        progressBarWidth = 4.dp,
                        roundBorder = true,
                        progressBarColor = color,
                        backgroundProgressBarColor = color.copy(0.5f),
                        backgroundProgressBarWidth = 4.dp
                    )

                    Text(
                        text = "${DigitHelper.digitByLang(animateProgress.toInt().toString())}%",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (animateProgress == 100f) MaterialTheme.colorScheme.softgray else MaterialTheme.colorScheme.darkText,
                        fontWeight = FontWeight.Bold
                    )
                }


                MySpacerWidth(width = 16.dp)

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.displaySmall,
                        maxLines = 1,
                        color = if (animateProgress == 100f) MaterialTheme.colorScheme.softgray else MaterialTheme.colorScheme.darkText,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth(),
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = if (animateProgress == 100f) TextDecoration.LineThrough else null
                    )

                    MySpacerHeight(height = 4.dp)

                    Text(
                        text = "${DigitHelper.digitByLang(task.subTask.size.toString())} ${stringResource(R.string.tasks)}",
                        style = MaterialTheme.typography.veryExtraSmall,
                        color = MaterialTheme.colorScheme.softgray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .wrapContentSize(),
                        textDecoration = if (animateProgress == 100f) TextDecoration.LineThrough else null
                    )

                }


            }


            Row(
                modifier = Modifier
                    .weight(0.1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {

                Box(
                    modifier = Modifier
                        .clip(Shapes().extraLarge)
                        .background(color)
                        .fillMaxHeight()
                        .width(6.dp)
                        .padding(16.dp)


                )

            }


        }

    }


}

@Preview()
@Composable
fun TaskItemPrev() {
    CheckListTheme {
        TaskItem(task = Task(
            0,
            "djkncjdcn",
            "dkcmkdc",
            3,
            23,
            3,
            3,
            "dfcc",
            listOf<Subtask>(Subtask())
        )) {

        }
    }
}