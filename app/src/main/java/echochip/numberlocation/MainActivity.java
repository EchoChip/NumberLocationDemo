package echochip.numberlocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.prefixmapper.PrefixFileReader;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText et_number;
    TextView tv_location;
    Button btn_location;

    Phonenumber.PhoneNumber structuredNumber;
    // /system/frameworks/ext.jar  86_zh
    PrefixFileReader prefix = new PrefixFileReader("/com/android/i18n/phonenumbers/geocoding/data/");
    //地区
    static Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_number = (EditText) findViewById(R.id.et_number);
        tv_location = (TextView) findViewById(R.id.tv_location);
        btn_location = (Button) findViewById(R.id.btn_location);

        //locale = getResources().getConfiguration().locale;
        locale = Locale.CHINA;

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    structuredNumber = PhoneNumberUtil.getInstance().parse(et_number.getText(), locale.getCountry());
                    String location = prefix.getDescriptionForNumber(structuredNumber, locale.getLanguage(), "", locale.getCountry());
                    tv_location.setText(location);
                } catch (NumberParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
