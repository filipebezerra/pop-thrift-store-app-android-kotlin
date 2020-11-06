package dev.filipebezerra.android.popthriftstore.ui.instructions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.filipebezerra.android.popthriftstore.databinding.InstructionItemViewBinding
import org.jetbrains.annotations.NotNull

class InstructionItemAdapter : RecyclerView.Adapter<InstructionItemViewHolder>() {

    private var instructionItems: List<InstructionItem> = emptyList()

    fun submitList(@NotNull newList: List<InstructionItem>) {
        if (newList == instructionItems) {
            return
        }

        instructionItems = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = instructionItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        InstructionItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: InstructionItemViewHolder, position: Int) =
        holder.bindTo(getItem(position))

    private fun getItem(position: Int) = instructionItems[position]
}

class InstructionItemViewHolder private constructor(
    private val itemViewBinding: InstructionItemViewBinding
) : RecyclerView.ViewHolder(itemViewBinding.root) {

    private val context: Context by lazy { itemView.context }

    fun bindTo(item: InstructionItem) {
        with(itemViewBinding) {
            titleText.text = context.getString(item.titleResource)
            descriptionText.text = context.getString(item.descriptionResource)
            instructionImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    item.imageResource
                )
            )
        }
    }

    companion object {
        fun from(parent: ViewGroup): InstructionItemViewHolder {
            val viewBinding = InstructionItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return InstructionItemViewHolder(viewBinding)
        }
    }
}