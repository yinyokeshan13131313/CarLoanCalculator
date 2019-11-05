package com.example.carloancalculator

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        buttonCalculate.setOnClickListener{
            calculateLoan()
        }
    }

    private fun calculateLoan(){

        //Validation
        if(editTextCarPrice.text.isEmpty()){
            editTextCarPrice.setError(getString(R.string.error_input))
            return //means stop, it will not continue
        }

        val carPrice: Float = editTextCarPrice.text.toString().toFloat()
        val downPayment: Float = editTextDownPayment.text.toString().toFloat()
        val loan: Float = carPrice - downPayment
        val symbol = Currency.getInstance(Locale.getDefault()).getSymbol()

        textViewCarLoan.setText(getString(R.string.loan) + "${symbol + String.format("%.2f", loan)}") //R.string.loan is used to display the word Loan:

        val interestRate: Float = editTextInterestRate.text.toString().toFloat() / 100
        val loanPeriod: Float = editTextLoanPeriod.text.toString().toFloat()
        val interest: Float = loan * interestRate * loanPeriod

        textViewInterest.setText(getString(R.string.interest) + "${symbol + String.format("%.2f", interest)}")

        val monthlyRepayment: Float = (loan + interest) / loanPeriod / 12

        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment) + "${symbol + String.format("%.2f", monthlyRepayment)}")

        Toast.makeText(this, "Calculate Loan", Toast.LENGTH_SHORT).show()
    }

    fun reset(view: View){
        editTextCarPrice.setText("")
        editTextDownPayment.setText("")
        editTextLoanPeriod.setText("")
        editTextInterestRate.setText("")
        textViewCarLoan.setText(getString(R.string.loan))
        textViewInterest.setText(getString(R.string.interest))
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment))
        Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
