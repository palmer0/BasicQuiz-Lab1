package es.ulpgc.eite.da.basicquizlab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

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

    trueButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        onTrueButtonClicked(v);
      }
    });

    falseButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        onFalseButtonClicked(v);
      }
    });

    nextButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        onNextButtonClicked(v);
      }
    });

    cheatButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        onCheatButtonClicked(v);
      }
    });
  }


  //TODO: impedir que podamos hacer click en el boton
  // si ya hemos contestado a la pregunta
  private void onTrueButtonClicked(View v) {

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
  private void onFalseButtonClicked(View v) {

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

  //TODO: implementar boton para pasar a siguiente pantalla
  private void onCheatButtonClicked(View v) {
    // no implementado
  }

  //TODO: impedir que podamos hacer click en el boton
  // si aun no hemos contestado a la pregunta
  private void onNextButtonClicked(View v) {

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
