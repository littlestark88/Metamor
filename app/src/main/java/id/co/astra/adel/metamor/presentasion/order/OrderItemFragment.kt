package id.co.astra.adel.metamor.presentasion.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.FragmentOrderItemBinding
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import id.co.astra.adel.metamor.domain.order.model.Order
import id.co.astra.adel.metamor.utils.Constants.ITEM_DATA
import id.co.astra.adel.metamor.utils.convertCurrency
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderItemFragment : DialogFragment() {

    private var _binding: FragmentOrderItemBinding? = null
    private val binding get() = _binding!!
    private var valueQuantity = 1
    private val orderViewModel: OrderViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOrderItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataItem = arguments?.getParcelable<AddItem>(ITEM_DATA)
        with(binding) {
            btnCancel.setOnClickListener {
                dismiss()
            }
            btnSave.setOnClickListener {
//                val valueDiscount = edtDiscount.text.toString()
//                if(valueDiscount.isNotEmpty()) {
//                    lifecycleScope.launch {
//                        orderViewModel.insertOrder(
////                            Order(
////                                0,
////                                dataItem?.nameItem.toString(),
////                                dataItem?.priceItem ?: 0.0,
////                                valueQuantity,
////                                valueDiscount.toInt()
////                            )
//                        )
//                    }
//                    dismiss()
//                } else {
//                    lifecycleScope.launch {
//                        orderViewModel.insertOrder(
//                            Order(
//                                0,
//                                dataItem?.nameItem.toString(),
//                                dataItem?.priceItem ?: 0.0,
//                                valueQuantity,
//                                0
//                            )
//                        )
//                    }
//                    dismiss()
//                }

            }

            switchDiscount.setOnClickListener {
                if (switchDiscount.isChecked) {
                    edtDiscount.isEnabled = true
                    edtDiscount.hint = 0.toString()
                } else {
                    edtDiscount.isEnabled = false
                }

            }
            btnPlus.setOnClickListener {
                valueQuantity++
                Toast.makeText(requireActivity(), valueQuantity.toString(), Toast.LENGTH_SHORT).show()
                tvQuantityValue.text = valueQuantity.toString()
            }
            btnMinus.setOnClickListener {
                if (valueQuantity <= 1) {
                    btnMinus.setImageResource(R.drawable.ic_minus)
                } else {
                    valueQuantity--
                    tvQuantityValue.text = valueQuantity.toString()
                }
            }
            tvNameItem.text = dataItem?.nameItem
            tvPrice.text = convertCurrency(dataItem?.priceItem)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
    }
}