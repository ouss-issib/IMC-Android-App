package enset.ma.bmicalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextPoids: EditText
    private lateinit var editTextTaille: EditText
    private lateinit var buttonCalculer: Button
    private lateinit var textViewResult: TextView
    private lateinit var textViewLabel: TextView
    private lateinit var imageViewCategorie: ImageView
    private lateinit var textViewCategorie: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liaison avec les composants XML
        editTextPoids = findViewById(R.id.editTextPoids)
        editTextTaille = findViewById(R.id.editTextTaille)
        buttonCalculer = findViewById(R.id.button_calculer)
        textViewResult = findViewById(R.id.textViewResult)
        textViewLabel = findViewById(R.id.textViewLabel)
        imageViewCategorie = findViewById(R.id.imageView2)
        textViewCategorie = findViewById(R.id.textView2)

        buttonCalculer.setOnClickListener {
            calculerIMC()
        }
    }

    private fun calculerIMC() {
        val poidsText = editTextPoids.text.toString()
        val tailleText = editTextTaille.text.toString()

        if (poidsText.isEmpty() || tailleText.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir le poids et la taille", Toast.LENGTH_SHORT).show()
            return
        }

        val poids = poidsText.toFloat()
        val tailleCm = tailleText.toFloat()
        val tailleM = tailleCm / 100

        if (tailleM <= 0) {
            Toast.makeText(this, "La taille doit être supérieure à 0", Toast.LENGTH_SHORT).show()
            return
        }

        val imc = poids / (tailleM * tailleM)
        val imcRounded = String.format("%.2f", imc)
        textViewResult.text = imcRounded

        when {
            imc < 18.5 -> {
                imageViewCategorie.setImageResource(R.drawable.maigre)
                textViewCategorie.text = "Maigreur"
            }
            imc < 25 -> {
                imageViewCategorie.setImageResource(R.drawable.normal)
                textViewCategorie.text = "Poids normal"
            }
            imc < 30 -> {
                imageViewCategorie.setImageResource(R.drawable.surpoids)
                textViewCategorie.text = "Surpoids"
            }
            imc < 40 -> {
                imageViewCategorie.setImageResource(R.drawable.obese)
                textViewCategorie.text = "Obésité modérée"
            }
            else -> {
                imageViewCategorie.setImageResource(R.drawable.t_obese)
                textViewCategorie.text = "Obésité sévère"
            }
        }
    }
}
