package dev.filipebezerra.android.popthriftstore.ui.productlist

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.data.Product

@BindingAdapter("pricing")
fun bindPricing(textView: TextView, product: Product) {
    if (product.discountedPrice == null) {
        textView.text =  textView.context.getString(
            R.string.pricing_format,
            product.price,
        )
        return
    }

    val pricingFormat = textView.context.getString(
        R.string.discounted_pricing_format,
        product.price,
        product.discountedPrice,
    )
    val lengthIndex = pricingFormat.length
    val whitespaceIndex = pricingFormat.indexOf(" ")

    val discountedPrice = SpannableStringBuilder(pricingFormat).apply {
        setSpan(
            StrikethroughSpan(),
            0,
            whitespaceIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            StyleSpan(Typeface.BOLD),
            whitespaceIndex + 1,
            lengthIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            RelativeSizeSpan(1.1f),
            whitespaceIndex + 1,
            lengthIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    textView.text = discountedPrice
}

@BindingAdapter("image")
fun bindImage(imageView: ImageView, product: Product) {
    Glide.with(imageView.context)
        .load(product.imageUrl)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.drawable.ic_outline_insert_photo_24)
        .error(R.drawable.ic_no_camera)
        .into(imageView)
}

@BindingAdapter("list")
fun bindList(listView: RecyclerView, items: List<Product>?) {
    items?.let { (listView.adapter as ProductAdapter).submitList(items) }
}