package com.example.android.justjava;
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int quantity = 2;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
       /** int numberOfCoffees = 4;
        display(numberOfCoffees);
        displayPrice(quantity * 5);
        */
        CheckBox whippedCreamBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        boolean hasWhippedCream = whippedCreamBox.isChecked();

        CheckBox chocolateBox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        boolean hasChocolate = chocolateBox.isChecked();

        EditText nameText = (EditText) findViewById(R.id.name_editText);
        String name = nameText.getText().toString();

       int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage = createOrderSummary(price,hasWhippedCream, hasChocolate, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order " +name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

       //displayMessage(priceMessage);


    }

    /**
     * Calculates the price of the order.
     *
     * @param //quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if(addWhippedCream){
            basePrice = basePrice +1;
        }

        if(addChocolate){
            basePrice = basePrice +2;
        }
        return  quantity * basePrice;
    }

    public String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String nameOfText){
        String priceMessage = "Name:" + nameOfText;
        //priceMessage +=  "\nName:" + nameOfText;
        priceMessage += "\nAdd Whipped Cream? "+ addWhippedCream;
        priceMessage += "\nAdd Chocolate? "+ addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            //Show an error message as a toast
            Toast.makeText(this,"You can't select more than 100 cup",Toast.LENGTH_SHORT ).show();
            return;
        }

        quantity += 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
       // int quantity = 1;
        if (quantity == 1) {
            //Show an error message as a toast
            Toast.makeText(this,"You can't select less than 1 cup",Toast.LENGTH_SHORT ).show();
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    /*private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
*/
}
