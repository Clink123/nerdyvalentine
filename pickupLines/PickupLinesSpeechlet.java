package pickupLines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class PickupLinesSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(PickupLinesSpeechlet.class);

    private static final String[] PICKUP_LINES = new String[] {
            "Is your name Google? Because you have everything I've been searching for.",
            "Is your name Wi-fi? Because I'm really feeling a connection.",
            "You've stolen the ASCII to my heart.",
            "Are you a computer keyboard? Because you're my type.",
            "Are you full of beryllium, gold, and titanium? You must be because you are BeAuTi-ful.",
            "You must be made of uranium and iodine because all I can see is U and I together.",
            "Forget hydrogen, you're my number one element.",
            "Are you made of copper and tellurium? Because you're CuTe",
            "My love for you is like dividing by zero-- it cannot be defined.",
            "If you were a triangle you'd be acute one.",
            "Are you the square root of negative one? Because you can't be real.",
            "I wish I were adenine because then I could get paired with U.",
            "I need some answers for my math homework. Quick. What's your number?",
            "I'm learning about important dates in history. Wanna be one of them?",
            "I want our love to be like pi, irrational and never ending.",
            "Tonight this Han doesn't want to fly solo.",
            "Were you forged by Sauron, because you are precious.",
            "You must have your phasers set to stunning.",
            "Plumbers are red, hedgehogs are blue. Press start to join, and be my player two.",
            "You must be the speed of light, because time stops when I look at you.",
            "I hope I'm not being obtuse, but you're acute person.",
            "Can I have your significant digits?",
            "The inhibition markers on my DNA must be blocked, because I can't seem to stop myself from hitting on you.",
            "You must be a high test score, because I want to take you home and show you to my mother.",
            "If you were President, you'd be Babe-raham Lincoln.",
            "You are the Renaissance to my Dark Ages, you light up my world.",
            "We must be subatomic particles, because I feel strong force between us."
     };

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getNewPickupLineResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("GetNewPickupLineIntent".equals(intentName)) {
            return getNewPickupLineResponse();

        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();

        } else if ("AMAZON.StopIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }


    private SpeechletResponse getNewPickupLineResponse() {
        int lineIndex = (int) Math.floor(Math.random() * PICKUP_LINES.length);
        String line = PICKUP_LINES[lineIndex];

        // Create speech output
        String speechText = line;

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Nerdy Valentine");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }


    private SpeechletResponse getHelpResponse() {
        String speechText =
                "You can ask Nerdy Valentine tell me a pickup line, or, you can say exit. What can I "
                        + "help you with?";

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }
}
