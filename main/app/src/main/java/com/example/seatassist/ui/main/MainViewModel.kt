package com.example.seatassist.ui.main

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.seatassist.data.MembersData
import com.example.seatassist.data.OffsetData
import com.example.seatassist.data.ScaleData

class MainViewModel : ViewModel() {

    var numberText = mutableStateOf("0")
    var numberTextNoneVisi = mutableStateOf(0)

    fun editNumber(newNumber: String) {
        numberText.value = newNumber
    }


    var offsetList = mutableStateListOf<OffsetData>()

    var sizeValue = mutableStateOf(50.dp)

    var color = mutableStateOf(Color(0xFFDBD2AC))

    fun addObject(id: Int, name: String, OffsetX: Float, OffsetY: Float, color: Color, size: Dp) {
        offsetList.add(
            OffsetData(
                id,
                name,
                mutableStateOf(OffsetX),
                mutableStateOf(OffsetY),
                color,
                size,
            )
        )
    }

    fun removeObject(id: Int) {
        offsetList.removeAt(id)
        if (id != offsetList.size) {
            for (i in id until offsetList.size) {
                offsetList[i].id -= 1
            }
        }
    }

    fun removeAllObject() {
        offsetList.clear()
    }

    fun moveOffsetX(id: Int, newOffsetX: Float) {
        offsetList[id].offsetX.value += newOffsetX
    }

    fun moveOffsetY(id: Int, newOffsetY: Float) {
        offsetList[id].offsetY.value += newOffsetY
    }


    var membersList = mutableStateListOf<MembersData>()
        private set

    fun addMember(id: Int, member: String) {
        membersList.add(MembersData(id, mutableStateOf(member)))
    }

    fun addMemberOne(id: Int, member: String) {
        membersList.add(MembersData(id, mutableStateOf(member)))
        if (numberText.value.toIntOrNull() != null) {
            numberText.value = (numberText.value.toInt() + 1).toString()
            numberTextNoneVisi.value += 1
        }
    }

    fun removeMember(id: Int) {
        membersList.removeAt(id)
        if (id != membersList.size) {
            for (i in id until membersList.size) {
                membersList[i].id -= 1
            }
        }
        numberText.value = (numberText.value.toInt() - 1).toString()
        numberTextNoneVisi.value -= 1
    }

    fun completionNumber(newNumber: Int) {
        membersList.clear()
        numberTextNoneVisi.value = newNumber
    }

    fun editName(id: Int, newName: String) {
        membersList[id].name.value = newName
    }


    // SizeScreen
    var scaleValue = ScaleData(scale = mutableStateOf(1F), rotation = mutableStateOf(0F))

    fun editSize(newSize: Dp) {
        sizeValue.value = newSize
    }

    fun editColor(newColor: Color) {
        color.value = newColor
    }

    fun editScale(newScale: Float, newRotation: Float) {
        when {
            sizeValue.value.value * newScale > 150F -> {
                sizeValue.value = 150.dp
                scaleValue.scale.value = 1F
                scaleValue.rotation.value += newRotation
            }
            sizeValue.value.value * newScale < 30F -> {
                sizeValue.value = 30.dp
                scaleValue.scale.value = 1F
                scaleValue.rotation.value += newRotation
            }
            else -> {
                sizeValue.value *= newScale
                scaleValue.rotation.value += newRotation
            }
        }
    }


    // Lottery Screen
    fun shuffleList() {
        membersList.shuffle()
    }


    // Number Screen
    fun noChangeMembers() {
        numberText.value = numberTextNoneVisi.value.toString()
    }
}