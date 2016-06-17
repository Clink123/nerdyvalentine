package pickupLines;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class PickupLinesSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;

    static {
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.echo-sdk-ams.app.23b99e6f-f559-4422-9ca3-6bac178e6eba");
    }

    public PickupLinesSpeechletRequestStreamHandler() {
        super(new PickupLinesSpeechlet(), supportedApplicationIds);
    }
}
