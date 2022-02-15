package es.ulpgc.eite.da.basicquizlab.test;

@SuppressWarnings("ALL")
public class QuizSteps {

  /*
  @Rule
  public ActivityTestRule<MainActivity> testRule =
      new ActivityTestRule(MainActivity.class, true, false);

  private Activity activity;


  @Before("@quiz-feature")
  public void setUp() {
    testRule.launchActivity(new Intent());
    activity = testRule.getActivity();
  }

  @After("@quiz-feature")
  public void tearDown() {
    testRule.finishActivity();
  }


  @Given("^iniciar pantalla Question$")
  public void iniciarPantallaQuestion() {

  }

  @And("^mostrar pregunta \"([^\"]*)\"$")
  public void mostrarPregunta(String q) {
    onView(withId(R.id.questionText)).check(matches(isDisplayed()));
    onView(withId(R.id.questionText)).check(matches(withText(q)));
  }



  @And("^ocultar resultado$")
  public void ocultarResultado() {
    //onView(withId(R.id.replyText)).check(matches(not(isDisplayed())));
    onView(withId(R.id.replyText)).check(matches(isDisplayed()));
    //onView(withId(R.id.replyText)).check(matches(withText("???")));
    onView(withId(R.id.replyText))
        .check(matches(withText(activity.getString(R.string.empty_text))));
  }


  @When("^pulsar boton \"([^\"]*)\"$")
  public void pulsarBoton(String b) {
    String tb = activity.getString(R.string.true_button_text);
    //int button = (b.equals("True")) ? R.id.trueButton : R.id.falseButton;
    int button = (b.equals(tb)) ? R.id.trueButton : R.id.falseButton;
    onView(withId(button)).check(matches(isDisplayed()));
    onView(withId(button)).perform(click());
  }

  @Then("^mostrar resultado \"([^\"]*)\" a respuesta \"([^\"]*)\"$")
  public void mostrarResultadoARespuesta(String r, String a) {
    onView(withId(R.id.replyText)).check(matches(isDisplayed()));
    onView(withId(R.id.replyText)).check(matches(withText(r)));
  }

  @When("^pulsar boton Next$")
  public void pulsarBotonNext() {
    onView(withId(R.id.nextButton)).check(matches(isDisplayed()));
    onView(withId(R.id.nextButton)).perform(click());
  }
  */

}
