package id.co.astra.adel.metamor.presentasion.saveorder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.astra.adel.metamor.databinding.ItemListSaveOrderBinding
import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrder

class SaveOrderAdapter(
    private val data: MutableList<SaveOrder>,
    private val onClickListener: (SaveOrder) -> Unit
): RecyclerView.Adapter<SaveOrderAdapter.SaveOrderViewHolder>() {

    fun setListSaveOrder(saveOrderList: List<SaveOrder>) {
        data.clear()
        data.addAll(saveOrderList)
        notifyItemChanged(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SaveOrderViewHolder {
        val binding = ItemListSaveOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaveOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaveOrderViewHolder, position: Int) {
        holder.bind(data[position], onClickListener)
    }

    override fun getItemCount(): Int = data.size

    inner class SaveOrderViewHolder(private val binding: ItemListSaveOrderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: SaveOrder,
            onClickListener: (SaveOrder) -> Unit
        ) {
            with(binding) {
                tvNameValue.text = data.nameCustomer
                tvPhoneNumberValue.text = data.numberPhone.toString()
            }
        }
    }
}