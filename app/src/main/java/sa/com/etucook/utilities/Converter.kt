package sa.com.etucook.utilities

import androidx.databinding.InverseMethod

object Converter {

    @JvmStatic
    @InverseMethod("stringToFloat")
    fun floatToString(value: Float) = value.toString()

    @JvmStatic
    fun stringToFloat(value: String) = value.toFloat()
}