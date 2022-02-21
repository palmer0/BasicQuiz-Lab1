package es.ulpgc.eite.da.basicquizlab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {

  public static final String TAG = "Quiz.CheatActivity";

  public final static String EXTRA_INDEX = "EXTRA_INDEX"; // key in

  public final static String EXTRA_ANSWER = "EXTRA_ANSWER";
  public final static String EXTRA_CHEATED = "EXTRA_CHEATED"; // key out

  private Button noButton, yesButton;
  private TextView answerText;

  private int currentAnswer;
  private boolean answerCheated;
  private int[] replyArray;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cheat);

    getSupportActionBar().setTitle(R.string.cheat_title);

    initLayoutData();

    linkLayoutComponents();
    enableLayoutButtons();
  }

  private void initLayoutData() {
    replyArray=getResources().getIntArray(R.array.reply_array);

    int questionIndex = getIntent().getExtras().getInt(EXTRA_INDEX);
    Log.d(TAG, "questionIndex: " + questionIndex);
    currentAnswer = replyArray[questionIndex];
    Log.d(TAG, "currentAnswer: " + currentAnswer);
  }


  private void linkLayoutComponents() {
    noButton = findViewById(R.id.noButton);
    yesButton = findViewById(R.id.yesButton);

    answerText = findViewById(R.id.answerText);
  }

  private void enableLayoutButtons() {

    noButton.setOnClickListener(v -> onNoButtonClicked());
    yesButton.setOnClickListener(v -> onYesButtonClicked());
  }

  private void onYesButtonClicked() {
    yesButton.setEnabled(false);
    noButton.setEnabled(false);

    if(currentAnswer == 0) {
      answerText.setText(R.string.false_text);
    } else {
      answerText.setText(R.string.true_text);

    }

    // indicar que se ha visto la respuesta correcta
    answerCheated = true;
    returnCheatedStatus();
  }

  private void onNoButtonClicked() {
    yesButton.setEnabled(false);
    noButton.setEnabled(false);

    //answerCheated = false;
    //returnCheatedStatus();
    finish();

  }

  private void returnCheatedStatus() {
    Log.d(TAG, "returnCheatedStatus()");
    Log.d(TAG, "answerCheated: " + answerCheated);

    Intent intent = new Intent();
    intent.putExtra(EXTRA_CHEATED, answerCheated);
    setResult(RESULT_OK, intent);

    //finish();
  }

  /*
  private void initLayoutData() {
    currentAnswer = getIntent().getExtras().getInt(EXTRA_ANSWER);
  }




  @Override
  public void onBackPressed() {
    Log.d(TAG, "onBackPressed()");

    returnCheatedStatus();
  }



  */
}
