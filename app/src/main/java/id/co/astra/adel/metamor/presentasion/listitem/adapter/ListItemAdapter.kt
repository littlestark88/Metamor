package id.co.astra.adel.metamor.presentasion.listitem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
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
    ) =
        AddViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_metamor, parent, false)
        )

    override fun onBindViewHolder(holder: AddViewHolder, position: Int) {
        holder.bind(data[position], onClickListener)
    }

    override fun getItemCount(): Int = data.size


    inner class AddViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMetamorBinding.bind(itemView)
        fun bind(
            data: AddItem,
            onClickListener: (AddItem) -> Unit
        ) {
            binding.tvName.text = data.nameItem
            binding.tvPricing.text = convertCurrency(data.priceItem)
            binding.imgPoster.load(data.imageItem)
            binding.imgDelete.setOnClickListener {
                onClickListener(data)
            }
        }
    }
}