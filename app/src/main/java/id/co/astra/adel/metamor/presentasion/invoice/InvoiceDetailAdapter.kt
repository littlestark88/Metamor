package id.co.astra.adel.metamor.presentasion.invoice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.astra.adel.metamor.databinding.ItemListInvoiceDetailBinding
import id.co.astra.adel.metamor.domain.checkout.model.CheckoutList
import id.co.astra.adel.metamor.utils.convertCurrency

class InvoiceDetailAdapter(
    private val data: List<CheckoutList>
) : RecyclerView.Adapter<InvoiceDetailAdapter.InvoiceDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceDetailViewHolder {
        val binding = ItemListInvoiceDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InvoiceDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InvoiceDetailViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class InvoiceDetailViewHolder(private val binding: ItemListInvoiceDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CheckoutList) {
            with(binding) {
                val totalPrice = data.priceItem * data.quantityItem
                tvNameItem.text = data.nameItem
                tvPriceItem.text = convertCurrency(data.priceItem)
                tvQuantity.text = data.quantityItem.toString()
                tvTotalPrice.text = convertCurrency(totalPrice)
            }
        }
    }
}