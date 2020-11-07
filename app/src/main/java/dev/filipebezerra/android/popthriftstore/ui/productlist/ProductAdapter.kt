package dev.filipebezerra.android.popthriftstore.ui.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.filipebezerra.android.popthriftstore.data.Product
import dev.filipebezerra.android.popthriftstore.databinding.ProductItemViewBinding

class ProductAdapter(
    private val viewModel: ProductListViewModel
) : ListAdapter<Product, ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder.from(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bindTo(viewModel, getItem(position))
}

class ProductViewHolder private constructor(
    private val itemViewBinding: ProductItemViewBinding
) : RecyclerView.ViewHolder(itemViewBinding.root) {

    fun bindTo(viewModel: ProductListViewModel, product: Product) {
        itemViewBinding.viewModel = viewModel
        itemViewBinding.product = product
        itemViewBinding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): ProductViewHolder {
            val itemViewBinding = ProductItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ProductViewHolder(itemViewBinding)
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) =
        oldItem == newItem
}