package infsolution.com.br.fidelize.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;

/**
 * Created by Cicero on 28/06/2016.
 */
public class DateTime extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private Button btDate;
    public String date="";

    public DateTime(View v){
        btDate = (Button)v;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
         this.date = day+" - "+(month+1)+" - "+year;
        btDate.setText(this.date);
    }
    public String getDate(){
        return this.date;
    }

}
