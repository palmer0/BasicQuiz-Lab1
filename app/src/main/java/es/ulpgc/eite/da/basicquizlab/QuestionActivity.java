package es.ulpgc.eite.da.basicquizlab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

  public static final String TAG = "Quiz.QuestionActivity";

  public static final int CHEAT_REQUEST = 1;

  private Button falseButton, trueButton,cheatButton, nextButton;
  private TextView questionText, replyText;

  private String[] questionArray;
  private int questionIndex=0;
  private int[] replyArray;
  private boolean nextButtonEnabled;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_question);

    getSupportActionBar().setTitle(R.string.question_title);

    initLayoutData();
    linkLayoutComponents();
    initLayoutContent();
    enableLayoutButtons();

    nextButton.setEnabled(false);
  }

  private void updateButtonsStatus() {
    nextButton.setEnabled(nextButtonEnabled);
    trueButton.setEnabled(!nextButtonEnabled);
    falseButton.setEnabled(!nextButtonEnabled);
    cheatButton.setEnabled(!nextButtonEnabled);
  }

  private void initLayoutData() {
    questionArray=getResources().getStringArray(R.array.question_array);
    replyArray=getResources().getIntArray(R.array.reply_array);
  }

  private void linkLayoutComponents() {
    falseButton = findViewById(R.id.falseButton);
    trueButton = findViewById(R.id.trueButton);
    cheatButton = findViewById(R.id.cheatButton);
    nextButton = findViewById(R.id.nextButton);

    questionText = findViewById(R.id.questionText);
    replyText = findViewById(R.id.replyText);
  }

  private void initLayoutContent() {
    questionText.setText(questionArray[questionIndex]);
    replyText.setText(R.string.empty_text);
  }

  private void enableLayoutButtons() {

    /*
    trueButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        onTrueButtonClicked();
      }
    });
    */

    trueButton.setOnClickListener(v -> {
      onTrueButtonClicked();
      // otras sentencias
    });

    falseButton.setOnClickListener(v -> onFalseButtonClicked());

    nextButton.setOnClickListener(v -> onNextButtonClicked());

    cheatButton.setOnClickListener(v -> onCheatButtonClicked());
  }


  //TODO: impedir que podamos hacer click en el boton
  // si ya hemos contestado a la pregunta
  private void onTrueButtonClicked() {

    /*
    if(nextButtonEnabled) {
      return;
    }
    */

    if(replyArray[questionIndex] == 1) {
      replyText.setText(R.string.correct_text);
    } else {
      replyText.setText(R.string.incorrect_text);
    }

    nextButtonEnabled = true;
    updateButtonsStatus();

    /*
    nextButton.setEnabled(nextButtonEnabled);
    trueButton.setEnabled(!nextButtonEnabled);
    falseButton.setEnabled(!nextButtonEnabled);
    cheatButton.setEnabled(!nextButtonEnabled);
    */
  }

  //TODO: impedir que podamos hacer click en el boton
  // si ya hemos contestado a la pregunta
  private void onFalseButtonClicked() {

    /*
    if(nextButtonEnabled) {
      return;
    }
    */

    if(replyArray[questionIndex] == 0) {
      replyText.setText(R.string.correct_text);
    } else {
      replyText.setText(R.string.incorrect_text);
    }

    nextButtonEnabled = true;
    updateButtonsStatus();

    /*
    nextButton.setEnabled(nextButtonEnabled);
    trueButton.setEnabled(!nextButtonEnabled);
    falseButton.setEnabled(!nextButtonEnabled);
    cheatButton.setEnabled(!nextButtonEnabled);
    */
  }

  //TODO: impedir que podamos hacer click en el boton
  // si aun no hemos contestado a la pregunta
  private void onNextButtonClicked() {

    /*
    if(!nextButtonEnabled) {
      return;
    }
    */

    nextButtonEnabled = false;
    updateButtonsStatus();

    /*
    nextButton.setEnabled(nextButtonEnabled);
    trueButton.setEnabled(!nextButtonEnabled);
    falseButton.setEnabled(!nextButtonEnabled);
    cheatButton.setEnabled(!nextButtonEnabled);
    */
    questionIndex++;

    // si queremos que el quiz acabe al llegar
    // a la ultima pregunta debemos comentar esta linea
    checkIndexData();

    if(questionIndex < questionArray.length) {
      initLayoutContent();
    }
  }


  //TODO: implementar boton para pasar a siguiente pantalla
  private void onCheatButtonClicked() {

    /*
    if(nextButtonEnabled) {
      return;
    }
    */

    Intent intent = new Intent(QuestionActivity.this, CheatActivity.class);
    //intent.putExtra(CheatActivity.EXTRA_ANSWER, replyArray[questionIndex]);
    intent.putExtra(CheatActivity.EXTRA_INDEX, questionIndex); // key-value
    //startActivity(intent);
    startActivityForResult(intent, CHEAT_REQUEST);
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    Log.d(TAG, "onActivityResult()");

    if (requestCode == CHEAT_REQUEST) {
      if (resultCode == RESULT_OK) {

        boolean answerCheated =
            data.getBooleanExtra(CheatActivity.EXTRA_CHEATED, false);

        Log.d(TAG, "answerCheated: " + answerCheated);


        // avanzar a siguiente pregunta
        if(answerCheated) {
          nextButtonEnabled = true;
          onNextButtonClicked();
        }


      }
    }
  }


  //TODO: refactorizar en un método este codigo
  // por si queremos implementar otras opciones posibles
  private void checkIndexData() {

    // hacemos que si llegamos al final del quiz
    // volvamos a empezarlo nuevamente
    if(questionIndex == questionArray.length) {
      questionIndex=0;
    }
  }
}
