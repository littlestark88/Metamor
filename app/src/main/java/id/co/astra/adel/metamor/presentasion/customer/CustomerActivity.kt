package id.co.astra.adel.metamor.presentasion.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.afollestad.materialdialogs.MaterialDialog
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.ActivityCustomerBinding
import id.co.astra.adel.metamor.domain.customer.model.Customer
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerBinding
    private val customerViewModel: CustomerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {

            btnSaveCustomer.setOnClickListener{
                saveDataCustomer()
                val dialog = MaterialDialog(this@CustomerActivity)
                    .cancelable(false)
                    .cancelOnTouchOutside(false)
                    .title(text = getString(R.string.label_notif))
                    .message(text = getString(R.string.label_confirm_delete_item))
                    .positiveButton(text = getString(R.string.label_ok)) {
                        it.dismiss()
                        with(binding) {
                            this.edtNameCustomer.text?.clear()
                            this.edtPhoneNumber.text?.clear()
                            this.edtEmail.text?.clear()
                        }
                    }
                dialog.show()
            }
        }
    }

    private fun saveDataCustomer() {
        with(binding) {
            val nameCustomer = edtNameCustomer.text.toString().trim()
            val phoneNumber = edtPhoneNumber.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val dataCustomer = Customer(
                0,
                nameCustomer,
                phoneNumber.toInt(),
                email
            )
            lifecycleScope.launch {
                customerViewModel.insertCustomer(dataCustomer)
            }
        }


    }
}