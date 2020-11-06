package dev.filipebezerra.android.popthriftstore.ui.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent

class InstructionsViewModel : ViewModel() {

    private val _navigateToHome = MutableLiveData<Event<Any>>()
    val navigateToHome: LiveData<Event<Any>>
        get() = _navigateToHome

    private val _instructionItems = MutableLiveData<List<InstructionItem>>()
    val instructionItems: LiveData<List<InstructionItem>>
        get() = _instructionItems

    private val _currentInstructionPosition = MutableLiveData<Int>()
    val currentInstructionPosition: LiveData<Int>
        get() = _currentInstructionPosition

    init {
        _instructionItems.value = listOf(
            InstructionItem(
                R.string.title_all_you_can_find_instruction,
                R.string.content_all_you_can_find_instruction,
                R.drawable.ic_all_you_can_find
            ),
            InstructionItem(
                R.string.title_save_money_instruction,
                R.string.content_save_money_instruction,
                R.drawable.ic_save_money
            ),
            InstructionItem(
                R.string.title_full_shopping_cart_instruction,
                R.string.content_full_shopping_cart_instruction,
                R.drawable.ic_full_shopping_cart
            ),
        ).apply {
            _currentInstructionPosition.value = this.withIndex().first().index
        }
    }

    fun skip() = finishInstructions()

    fun next() {
        if (currentInstructionPosition.value == instructionItems.value?.size) {
            finishInstructions()
            return
        }

        setCurrentInstructionPosition(currentInstructionPosition.value!!.inc())
    }

    fun changeCurrentInstructionPosition(position: Int) =
        setCurrentInstructionPosition(position)

    private fun setCurrentInstructionPosition(position: Int) {
        if (position == currentInstructionPosition.value) return

        synchronized(this) {
            _currentInstructionPosition.value = position
        }
    }

    private fun finishInstructions() = _navigateToHome.postEvent(true)
}