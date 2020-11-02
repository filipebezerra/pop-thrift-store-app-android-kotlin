package dev.filipebezerra.android.popthriftstore.ui.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dev.filipebezerra.android.popthriftstore.R

class InstructionFragment : Fragment() {

    private val instructionViewModel: InstructionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.instruction_fragment, container, false)
    }

}