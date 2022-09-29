package id.co.astra.adel.metamor.presentasion.invoice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.ItemListInvoiceDetailBinding
import id.co.astra.adel.metamor.domain.checkout.model.CheckoutList
import id.co.astra.adel.metamor.utils.convertCurrency

class InvoiceDetailAdapter(
    private val context: Context,
    private val data: List<CheckoutList>
) : RecyclerView.Adapter<InvoiceDetailAdapter.InvoiceDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        InvoiceDetailViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_invoice_detail, parent, false)
        )

    override fun onBindViewHolder(holder: InvoiceDetailViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class InvoiceDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListInvoiceDetailBinding.bind(itemView)
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