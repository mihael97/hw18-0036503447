package macuka.mihael.hw18_0036503447;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class ComposeMailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_mail_activiy);

        Button sendButton=findViewById(R.id.btn_send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method is called when button for sending mails is clicked<br>
             * Before sending,method checks if every filed is not null and is not empty. If some filed is not valid,method will show
             * appropriate message
             * @param view - view where button is clicked
             */
            @Override
            public void onClick(View view) {
                try {
                    String email = findViewById(R.id.email).toString();
                    String title = findViewById(R.id.title).toString();
                    String text = findViewById(R.id.message).toString();

                    System.out.print(email);

                    //checks if all fields are not null and not empty
                    if (!(checkIfValid(email) && checkIfValid(title) && checkIfValid(text))) {
                        showError("Fields cannot be null or empty!");
                        return;
                    }

                    //first title letter must be uppercase
                    if(!checkLetter(title.charAt(0))) {
                        showError("First title letter must be uppercase letter!");
                        return;
                    }

                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL,email);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_CC,new String[]{"macothelord@yahoo.com","mihael.macuka@fer.hr"});
                    intent.putExtra(Intent.EXTRA_SUBJECT,title);
                    intent.putExtra(Intent.EXTRA_TEXT,text);

                    startActivity(Intent.createChooser(intent,"Send to:"));
                } catch (Exception e) {
                   showError("Error");
                }
            }
        });
    }

    /**
     * Auxiliary methods
     */

    /**
     * Method checks if letter is valid<br>
     * Method is valid when it's integer representation is between 65 and 90
     * @param c - character we check
     * @return <code>true</code> if letter is valid,otherwise <code>false</code>
     */
    private boolean checkLetter(char c) {
        int value=c;

        return value>=65 && value<=90;
    }

    /**
     * Method shows error message in pop out screen
     * @param message - message for show
     */
    private void showError(String message) {
        Toast.makeText(ComposeMailActivity.this,message, Toast.LENGTH_LONG);
    }

    /**
     * Method checks if filed text valid<br>
     *     Text is valid when it is not null and it is not empty
     * @param parameter - string we check
     * @return <code>true</code> if text is valid,otherwise <code>false</code>
     */
    private boolean checkIfValid(String parameter) {
        return parameter!=null && !parameter.isEmpty();
    }
}
