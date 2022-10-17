package id.co.astra.adel.metamor.presentasion.listitem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.ItemListMetamorBinding
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import id.co.astra.adel.metamor.utils.convertCurrency

class ListItemAdapter(
    private val context: Context,
    private val data: MutableList<AddItem>,
    private val onClickListener: (AddItem) -> Unit
) : RecyclerView.Adapter<ListItemAdapter.AddViewHolder>() {

    fun setListMetamor(addItemAddList: List<AddItem>) {
        data.clear()
        data.addAll(addItemAddList)
        notifyItemChanged(itemCount)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddViewHolder {
        val binding = ItemListMetamorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddViewHolder, position: Int) {
        holder.bind(data[position], onClickListener)
    }

    override fun getItemCount(): Int = data.size


    inner class AddViewHolder(private val binding: ItemListMetamorBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: AddItem,
            onClickListener: (AddItem) -> Unit
        ) {
            with(binding) {
                tvName.text = data.nameItem
                tvPricing.text = convertCurrency(data.priceItem)
                Glide
                    .with(context)
                    .load(data.imageItem)
                    .centerCrop()
                    .into(imgPoster)
                imgDelete.setOnClickListener {
                    onClickListener(data)
                }
            }
        }
    }
}