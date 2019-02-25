package sa.com.etucook.utilities

import androidx.databinding.InverseMethod

object Converter {

    @JvmStatic
    @InverseMethod("stringToFloat")
    fun floatToString(value: Float) = if(value == 0F) "" else value.toString()

    @JvmStatic
    fun stringToFloat(value: String) = if (value.isBlank()) 0F else value.toFloat()

    @JvmStatic
    fun addEuros(value: String) = "$value €"
}