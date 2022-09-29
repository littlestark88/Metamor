package id.co.astra.adel.metamor.presentasion.setting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.converter.ArabicConverter
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.RawPrintable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import id.co.astra.adel.metamor.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding
    private var printing : Printing? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Printooth.hasPairedPrinter())
            printing = Printooth.printer()
        initViews()
        with(binding) {
            this?.btnBluetooth?.setOnClickListener {
                    if (Printooth.hasPairedPrinter()) Printooth.removeCurrentPrinter()
                    else startActivityForResult(Intent(context, ScanningActivity::class.java),
                        ScanningActivity.SCANNING_FOR_PRINTER)
                    initViews()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK)
        initViews()
    }


    private fun initViews() {
        with(binding) {
            this?.btnBluetooth?.text = if(Printooth.hasPairedPrinter()) "Terhubung" else "Tidak Terhubung"
        }
    }

}