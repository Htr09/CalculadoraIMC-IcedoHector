package icedo.hector.asignacion4_calculadora_imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var estatura:Float =0.0f
    var peso:Float=0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etEstatura: EditText = findViewById(R.id.etEstatura)
        val etPeso: EditText = findViewById(R.id.etPeso)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)
        val tvResultado: TextView = findViewById(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            val estaturaStr = etEstatura.text.toString()
            val pesoStr = etPeso.text.toString()

            if (estaturaStr.isNotEmpty() && pesoStr.isNotEmpty()) {
                estatura = estaturaStr.toFloat() / 100
                peso = pesoStr.toFloat()

                val imc = calcularIMC(peso, estatura)
                val clasificacion = obtenerClasificacionIMC(imc)

                tvResultado.text = "IMC: %.2f\n%s".format(imc, clasificacion)
            } else {
                tvResultado.text = "Por favor ingrese valores válidos."
            }
        }
    }

private fun calcularIMC(peso: Float, estatura: Float): Float {
    return peso / (estatura * estatura)
}

    private fun obtenerClasificacionIMC(imc: Float): String {
        return when {
            imc < 18.5 -> "Bajo peso"
            imc < 24.9 -> "Normal"
            imc < 29.9 -> "Sobrepeso"
            imc < 34.9 -> "Obesidad grado 1"
            imc < 39.9 -> "Obesidad grado 2"
            else -> "Obesidad grado 3"
        }
    }

}